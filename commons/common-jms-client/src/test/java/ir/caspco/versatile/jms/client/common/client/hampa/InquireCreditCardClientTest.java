package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.InquireCreditCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.InquireCreditCardResponse;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        InquireCreditCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class InquireCreditCardClientTest {

    @Autowired
    InquireCreditCardClient inquireCreditCardClient;

    @Test
    void inquire() {

        InquireCreditCardRequest inquireCreditCardRequest = InquireCreditCardRequest.builder()
                .nationalCode("3399885577")
                .build();

        InquireCreditCardResponse inquireCreditCardResponse = inquireCreditCardClient.send(inquireCreditCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(inquireCreditCardResponse);
    }
}