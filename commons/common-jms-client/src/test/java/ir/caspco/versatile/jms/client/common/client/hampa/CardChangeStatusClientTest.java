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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        CardChangeStatusClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class CardChangeStatusClientTest {

    @Autowired
    private CardChangeStatusClient cardChangeStatusClient;

    @Test
    void CardChangeStatus() {
        CardChangeStatusRequest cardChangeStatusRequest = CardChangeStatusRequest.builder()
                .cardNumber("6221061210989879")
                .build();

        CardChangeStatusResponse cardChangeStatusResponse = cardChangeStatusClient.send(cardChangeStatusRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(cardChangeStatusResponse);
    }
}
