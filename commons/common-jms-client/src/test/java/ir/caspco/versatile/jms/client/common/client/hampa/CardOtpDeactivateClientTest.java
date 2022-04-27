package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardOtpDeactivateRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardOtpDeactivateResponse;
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
        CardOtpDeactivateClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class CardOtpDeactivateClientTest {

    @Autowired
    CardOtpDeactivateClient cardOtpDeactivateClient;

    @Test
    void cardOtpDeactivate() {

        CardOtpDeactivateRequest cardOtpDeactivateRequest = CardOtpDeactivateRequest.builder()
                .cardNumber("6221061210989879")
                .build();

        CardOtpDeactivateResponse cardOtpDeactivateResponse = cardOtpDeactivateClient.send(cardOtpDeactivateRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(cardOtpDeactivateResponse);
    }
}