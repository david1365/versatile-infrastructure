package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CardChangeStatusResponse;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Date: 2/5/2022
 * User: khalili_f
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        ActiveHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class ActiveHampaCardClientTest {

    @Autowired
    private ActiveHampaCardClient activeHampaCardClient;


    @Test
    void CardChangeStatus() {
        CardChangeStatusRequest cardChangeStatusRequest = CardChangeStatusRequest.builder()
                .cardNumber("6221061210989879")
                .build();

        CardChangeStatusResponse cardChangeStatusResponse = activeHampaCardClient.send(cardChangeStatusRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(cardChangeStatusResponse);
    }

}
