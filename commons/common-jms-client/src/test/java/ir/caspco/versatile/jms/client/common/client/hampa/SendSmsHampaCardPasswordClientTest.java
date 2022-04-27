package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.SendSmsHampaCardPasswordRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.SendSmsHampaCardPasswordResponse;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        SendSmsHampaCardPasswordClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class SendSmsHampaCardPasswordClientTest {

    @Autowired
    SendSmsHampaCardPasswordClient sendSmsHampaCardPasswordClient;

    @Test
    void hampaCardList() {

        SendSmsHampaCardPasswordRequest sendSmsHampaCardPasswordRequest = SendSmsHampaCardPasswordRequest.builder()
                .cardNumber("6221061210989580")
                .build();

        SendSmsHampaCardPasswordResponse sendSmsHampaCardPasswordResponse = sendSmsHampaCardPasswordClient.send(sendSmsHampaCardPasswordRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(sendSmsHampaCardPasswordResponse);

        assertTrue(sendSmsHampaCardPasswordResponse.getResult());

    }


}
