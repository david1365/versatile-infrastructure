package ir.caspco.versatile.jms.client;

import ir.caspco.versatile.context.handler.exceptions.annotations.MessagesPath;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jndi.JndiTemplate;

import javax.naming.Context;
import java.util.Properties;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@PropertySource("classpath:jms-client.properties")
@MessagesPath("classpath:/i18n/jms-client-messages")
public class JmsClientConfiguration {

    private final JmsClientProperties jmsClientProperties;

    public JmsClientConfiguration(JmsClientProperties jmsClientProperties) {
        this.jmsClientProperties = jmsClientProperties;
    }

    @Bean
    public JndiTemplate jndiTemplate() {
        JndiTemplate template = new JndiTemplate();
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        properties.setProperty(Context.PROVIDER_URL, jmsClientProperties.getWebLogicUrl());

        template.setEnvironment(properties);

        return template;
    }
}
