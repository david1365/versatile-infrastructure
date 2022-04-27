package ir.caspco.versatile.jms.client.common.client.hampa;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.hampa.BillPaymentByHampaCardRequest;
import ir.caspco.versatile.jms.client.common.msg.hampa.BillPaymentByHampaCardResponse;
import ir.caspco.versatile.jms.client.common.vo.HampaBillCriteriaVO;
import ir.caspco.versatile.jms.client.common.vo.PaymentInformationVO;
import ir.caspco.versatile.jms.client.common.vo.hampa.BillPaymentByHampaCardRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JmsClientProperties.class,
        JmsClientConfiguration.class,
        BillPaymentByHampaCardClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
class BillPaymentByHampaCardClientTest {

    @Autowired
    BillPaymentByHampaCardClient billPaymentByHampaCardClient;

    @Test
    void buyProduct() {

        BillPaymentByHampaCardRequest billPaymentByHampaCardRequest = BillPaymentByHampaCardRequest.builder()
                .billPaymentByHampaCardRequest(
                        BillPaymentByHampaCardRequestVO.builder()
                                .hampaBillCriteria(HampaBillCriteriaVO.builder()
                                        .segmentCode(null)
                                        .paymentNumber(String.valueOf(123456))
                                        .billNumber(String.valueOf(123))
                                        .customerNumber(59906049L)
                                        .cardNumber("6221061209485335")
                                        .accountNumber("470004960566")
                                        .trackingNumber(111)
                                        .paymentInformation(PaymentInformationVO.builder()
                                                .accountRef("470004960566")
                                                .isCash(true)
                                                .amount(BigDecimal.valueOf(10))
                                                .build()
                                        )
                                        .rrn("6221061209485335")
                                        .channelType("MOBILE_BANK")
                                        .build())
                                .build()
                )
                .build();

        BillPaymentByHampaCardResponse billPaymentByHampaCardResponse = billPaymentByHampaCardClient.send(billPaymentByHampaCardRequest)
                .retrieve()
                .result()
                .orElse(null);

        assertNotNull(billPaymentByHampaCardResponse);
    }
}