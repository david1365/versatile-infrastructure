package ir.caspco.versatile.jms.client;

import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.common.util.reflect.GReflect;
import ir.caspco.versatile.context.common.util.result.Result;
import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.context.jms.client.enums.MessageType;
import ir.caspco.versatile.context.jms.client.enums.RequestType;
import ir.caspco.versatile.context.jms.client.exceptions.ClientConfigException;
import ir.caspco.versatile.context.jms.client.exceptions.CoreException;
import ir.caspco.versatile.context.jms.client.model.FaultDetails;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.IllegalStateException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.zip.GZIPInputStream;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Slf4j
public abstract class JmsClient<I, R> implements GReflect {

    private final Charset defaultCharsets = StandardCharsets.UTF_8;

    @Autowired
    private JmsClientProperties jmsClientProperties;

    @Autowired
    private JndiTemplate jndiTemplate;

    private Destination requestQueue;
    private Destination responseQueue;
    private JmsTemplate template;


    @SneakyThrows
    private JmsTemplate jmsTemplate() {

        if (template == null) {

            JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
            factoryBean.setJndiTemplate(jndiTemplate);
            factoryBean.setJndiName(jmsClientProperties.getConnectionFactory());
            factoryBean.setExpectedType(ConnectionFactory.class);
            factoryBean.afterPropertiesSet();
            ConnectionFactory connectionFactory = (ConnectionFactory) factoryBean.getObject();

            JndiObjectFactoryBean jndiRequestBean = new JndiObjectFactoryBean();
            jndiRequestBean.setJndiTemplate(jndiTemplate);
            jndiRequestBean.setJndiName(jmsClientProperties.getRequestQueue());
            jndiRequestBean.setExpectedType(Destination.class);
            jndiRequestBean.afterPropertiesSet();
            requestQueue = (Destination) jndiRequestBean.getObject();

            JndiObjectFactoryBean jndiResponseBean = new JndiObjectFactoryBean();
            jndiResponseBean.setJndiTemplate(jndiTemplate);
            jndiResponseBean.setJndiName(jmsClientProperties.getResponseQueue());
            jndiResponseBean.setExpectedType(Destination.class);
            jndiResponseBean.afterPropertiesSet();
            responseQueue = (Destination) jndiResponseBean.getObject();


            template = new JmsTemplate();
            template.setConnectionFactory(connectionFactory);
            template.setDefaultDestination(requestQueue);
            template.setReceiveTimeout(jmsClientProperties.getQueueReceiveTimeout());
            template.setTimeToLive(jmsClientProperties.getQueueReceiveTimeout());
            template.setSessionTransacted(true);
            template.setSessionAcknowledgeMode(1);
            template.afterPropertiesSet();
        }

        return template;
    }

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result<R> send(I input) {

        if (!isAnnotationPresent(JmsHeader.class)) {

            throw new ClientConfigException();
        }

        JmsHeader jmsHeader = getAnnotation(JmsHeader.class);

        String serviceId = jmsHeader.serviceId();
        RequestType requestType = jmsHeader.requestType();


        final String RANDOM_STRING = createRandomString();
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();

        return Result.accept(() -> sendAndReceive(input, serviceId, requestType, RANDOM_STRING, randomLong));
    }

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String send(String serviceId, RequestType requestType, I input) {

        final String RANDOM_STRING = createRandomString();
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();

        jmsTemplate().send(requestQueue, session -> getMessage(input, serviceId, requestType, RANDOM_STRING, randomLong, session));

        return RANDOM_STRING;

    }

    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public R receive(String correlationId) {

        Message receive = jmsTemplate().receiveSelected(responseQueue, String.format("JMSCorrelationID='%s'", correlationId));

        return getBody(receive);

    }


    public R sendAndReceive(I input, String serviceId, RequestType requestType, String RANDOM_STRING, long randomLong) {
        Message receive = jmsTemplate().sendAndReceive(session -> getMessage(input, serviceId, requestType, RANDOM_STRING, randomLong, session));

        return getBody(receive);
    }

    @SneakyThrows
    private Message getMessage(I input, String serviceId, RequestType requestType, String RANDOM_STRING, long randomLong, Session session) {
        BytesMessage message = getBytesMessage(serviceId, requestType, RANDOM_STRING, randomLong, session);

        try {

            message.writeBytes(Shift.just(input).toBytes());
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return message;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private R getBody(Message receive) {
        if (receive instanceof BytesMessage) {

            String responseType = receive.getStringProperty("responseType");
            String s = extractMessageBody(receive);

            if ("FAILED".equals(responseType)) {

                FaultDetails faultDetails = Shift.just(s).toShift(FaultDetails.class).toObject();

                log.error("CoreException: " + faultDetails.getDebugInfo());

                throw new CoreException(faultDetails);

            } else {

                Class<R> outputType = (Class<R>) genericClassObjects()[1];
                return Shift.just(s).toShift(outputType).toObject();

            }
        }

        log.error("CoreException: Timed out...");

        throw new CoreException("Timed out...");
    }

    @SneakyThrows
    private BytesMessage getBytesMessage(String serviceId, RequestType requestType, String RANDOM_STRING, long randomLong, Session session) {

        BytesMessage message = session.createBytesMessage();
        message.setJMSCorrelationID(RANDOM_STRING);
        message.setStringProperty("channel", jmsClientProperties.getChannel());
        message.setStringProperty("clientVersion", jmsClientProperties.getVersion());
        message.setStringProperty("gatewayVersion", jmsClientProperties.getVersion());
        message.setStringProperty("filter", jmsClientProperties.getFilter());
        message.setStringProperty("serviceId", serviceId);
        message.setStringProperty("payloadSchema", "object/json");
        message.setStringProperty("messageType", MessageType.REQUEST.name());
        message.setStringProperty("requestType", requestType.name());
        message.setStringProperty("securityAliasName", "lotus-host");
        message.setStringProperty("transactionType", "INPUT");
        message.setStringProperty("userCredentials", jmsClientProperties.getCredentials());
        message.setStringProperty("transactionId", Long.toHexString(randomLong));

        return message;

    }

    private static String createRandomString() {

        return Long.toHexString(new Random(System.currentTimeMillis()).nextLong());
    }

    @SneakyThrows
    private String extractMessageBody(Message receive) {

        if (receive instanceof BytesMessage) {

            BytesMessage bytesMessage = (BytesMessage) receive;
            byte[] buffer = new byte[(int) bytesMessage.getBodyLength()];
            bytesMessage.readBytes(buffer);

            if (receive.getBooleanProperty("compressed")) {

                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                final GZIPInputStream gis = new GZIPInputStream(bais);
                BufferedReader bf = new BufferedReader(new InputStreamReader(gis, defaultCharsets));

                StringBuilder outStr = new StringBuilder();
                String line;
                while ((line = bf.readLine()) != null) {

                    outStr.append(line);
                }
                bais.close();
                gis.close();
                bf.close();
                return outStr.toString();
            }

            return new String(buffer, defaultCharsets);
        } else {

            throw new IllegalStateException("Text message not supported");
        }
    }
}
