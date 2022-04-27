package ir.caspco.versatile.jms.client;

import ir.caspco.versatile.context.jms.client.enums.RequestType;
import ir.caspco.versatile.context.jms.client.exceptions.ClientConfigException;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import ir.caspco.versatile.jms.client.message.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        CustomerClient.class,
        CustomerClientNoConfig.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class JmsClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    CustomerClientNoConfig customerClientNoConfig;

    @Test
    void sendReceive() {

        Customer customer = Customer.builder()
                .clientId(26885319L)
                .build();

        String correlationId = customerClient.send("channelManagement.GetCustomerRelationsMsg", RequestType.INQUIRY, customer);

        Customer customerR = customerClient.receive(correlationId);

    }

    @Test
    void sendThrowsTest() {

        Customer customer = Customer.builder()
                .clientId(26885319L)
                .build();

        try {
            customerClientNoConfig.send(customer);

            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ClientConfigException);
        }

    }

    @Test
    void send() {

        Customer customer = Customer.builder()
                .clientId(26885319L)
                .build();


        customerClient.send(customer);

    }

}