package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardOtpRegisterRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardOtpRegisterResponse;
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
        CardOtpRegisterClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class CardOtpRegisterClientTest {

    @Autowired
    CardOtpRegisterClient cardOtpRegisterClient;

    @Test
    void cardOtpRegister() {

        CardOtpRegisterRequest cardOtpRegisterRequest = CardOtpRegisterRequest.builder()
                .cardNumber("6221061210990869")
                .build();

        CardOtpRegisterResponse cardOtpRegisterResponse = cardOtpRegisterClient.send(cardOtpRegisterRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(cardOtpRegisterResponse);

    }

}