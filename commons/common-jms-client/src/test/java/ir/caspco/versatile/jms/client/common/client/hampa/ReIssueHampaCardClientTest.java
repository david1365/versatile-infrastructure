package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.ReIssueHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.ReIssueHampaCardResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.ReIssueHampaCardEntranceVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        ReIssueHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class ReIssueHampaCardClientTest {

    @Autowired
    ReIssueHampaCardClient reIssueHampaCardClient;

    @Test
    void reIssueHampaCard() {

        ReIssueHampaCardRequest reIssueHampaCardRequest = ReIssueHampaCardRequest.builder()
                .reIssueHampaCardEntrance(
                        ReIssueHampaCardEntranceVO.builder()
                                .cardNumber("6221061210990869")
                                .build()
                )
                .build();
        ReIssueHampaCardResponse reIssueHampaCardResponse = reIssueHampaCardClient.send(reIssueHampaCardRequest).retrieve().result().orElse(null);

        String expectedCardNumber = "6221061210990869";

        assertNotNull(reIssueHampaCardResponse);

        assertNotNull(reIssueHampaCardResponse.getReIssueHampaCardResult());

        assertEquals(expectedCardNumber, reIssueHampaCardResponse.getReIssueHampaCardResult().getCardNumber());
    }
}