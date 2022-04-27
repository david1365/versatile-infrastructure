package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.CashOutHampaCardByAccountRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.CashOutHampaCardByAccountResponse;
import ir.caspco.versatile.jms.client.common.vo.hampa.CashOutHampaCardRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

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
        CashOutHampaCardByAccountClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class CashOutHampaCardByAccountClientTest {

    @Autowired
    CashOutHampaCardByAccountClient cashOutHampaCardByAccountClient;

    @Test
    void CashOutHampaCard() {

        CashOutHampaCardByAccountRequest cashOutHampaCardByAccountRequest = CashOutHampaCardByAccountRequest.builder()
                .cashOutHampaCardRequest(CashOutHampaCardRequestVO.builder()
                        .cardNumber("6221061210992113")
                        .segmentCode("01")
                        .accountNumber("72000122166007")
                        .amount(BigDecimal.valueOf(1))
                        .checkUniqueTrackingCode(false)
                        .uniqueTrackingCode("123456")
                        .build())
                .build();

        CashOutHampaCardByAccountResponse cashOutHampaCardByAccountResponse = cashOutHampaCardByAccountClient.send(cashOutHampaCardByAccountRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(cashOutHampaCardByAccountResponse);
    }
}