package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.ValidateActiveHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.ValidateActiveHampaCardResponse;
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
        ValidateActiveHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class ValidateActiveHampaCardClientTest {

    @Autowired
    ValidateActiveHampaCardClient validateActiveHampaCardClient;

    @Test
    void validateCard() {

        ValidateActiveHampaCardRequest validateActiveHampaCardRequest = ValidateActiveHampaCardRequest.builder()
                .cardNumber("6221061210993053")
                .build();

        ValidateActiveHampaCardResponse validateActiveHampaCardResponse = validateActiveHampaCardClient.send(validateActiveHampaCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(validateActiveHampaCardResponse);
    }
}