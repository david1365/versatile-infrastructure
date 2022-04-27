package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.ChangeFirstHampaPasswordRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.ChangeFirstHampaPasswordResponse;
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
        ChangeFirstHampaPasswordClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class ChangeFirstHampaPasswordClientTest {

    @Autowired
    ChangeFirstHampaPasswordClient changeFirstHampaPasswordClient;

    @Test
    void changeFirstHampaPassword() {

        ChangeFirstHampaPasswordRequest changeFirstHampaPasswordRequest = ChangeFirstHampaPasswordRequest.builder()
                .cardNumber("6221061210989671")
                .passwordType(1)
                .password("1532")
                .build();

        ChangeFirstHampaPasswordResponse changeFirstHampaPasswordResponse = changeFirstHampaPasswordClient.send(changeFirstHampaPasswordRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(changeFirstHampaPasswordResponse);
    }
}