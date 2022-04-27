package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.GetHampaStatementRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.GetHampaStatementResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.StatementRequestVO;
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
        GetHampaStatementClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class GetHampaStatementClientTest {

    @Autowired
    GetHampaStatementClient getHampaStatementClient;

    @Test
    void getHampaStatement() {

        GetHampaStatementRequest getHampaStatementRequest = GetHampaStatementRequest.builder()
                .statementRequest(
                        StatementRequestVO.builder()
                                .cardNumber("6221061210990844")
                                .segmentCode("01")
                                .length(10L)
                                .offset(1L)
                                .build()
                )
                .build();

        GetHampaStatementResponse getHampaStatementResponse = getHampaStatementClient.send(getHampaStatementRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(getHampaStatementResponse);

    }

}