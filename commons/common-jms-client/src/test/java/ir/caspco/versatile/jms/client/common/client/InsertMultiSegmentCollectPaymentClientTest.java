package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.InsertMultiSegmentCollectPaymentRequest;
import ir.caspco.versatile.jms.client.common.msg.InsertMultiSegmentCollectPaymentResponse;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentRequestVO;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentResponseVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        InsertMultiSegmentCollectPaymentClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class InsertMultiSegmentCollectPaymentClientTest {

    @Autowired
    private InsertMultiSegmentCollectPaymentClient insertMultiSegmentCollectPaymentClient;

    @Test
    void collectiveCreditBillPayment() {

        InsertMultiSegmentCollectPaymentRequest insertMultiSegmentCollectPaymentRequest = getInsertMultiSegmentCollectPaymentRequest();

        InsertMultiSegmentCollectPaymentResponse insertMultiSegmentCollectPaymentResponse =
                insertMultiSegmentCollectPaymentClient.send(insertMultiSegmentCollectPaymentRequest)
                        .retrieve()
                        .result()
                        .orElse(null);

        assertNotNull(insertMultiSegmentCollectPaymentResponse);
    }

    @Test
    void creditBillPayment() {
        InsertMultiSegmentCollectPaymentRequest insertMultiSegmentCollectPaymentRequest = getInsertMultiSegmentCollectPaymentRequest();

        List<CollectPaymentResponseVO> collectPaymentResponses =
                insertMultiSegmentCollectPaymentClient.creditBillPayment(insertMultiSegmentCollectPaymentRequest.getCollectPaymentRequests());

        assertNotNull(collectPaymentResponses);
        assertFalse(collectPaymentResponses.isEmpty());
    }

    private InsertMultiSegmentCollectPaymentRequest getInsertMultiSegmentCollectPaymentRequest() {
        List<CollectPaymentRequestVO> collectPaymentRequests = new ArrayList<>();

        CollectPaymentRequestVO collectPaymentRequest = CollectPaymentRequestVO.builder()
                .cardNumber("6221061210999654")
                .segmentCode("OTR")
                .externalRef("47006147111605")
                .amount(BigDecimal.valueOf(1))
                .clientTrackingCode("99f6e29f-966f-4bf4-bf66-5e7da82e7b27")
                .build();

        collectPaymentRequests.add(collectPaymentRequest);

        collectPaymentRequests.add(CollectPaymentRequestVO.builder()
                .cardNumber("6221061210999654")
                .segmentCode("OTR")
                .externalRef("47006147111605")
                .amount(BigDecimal.valueOf(1))
                .clientTrackingCode("99f6e29f-966f-4bf4-bf67-6e7da82e7b27")
                .build());


        return InsertMultiSegmentCollectPaymentRequest.builder()
                .collectPaymentRequests(
                        collectPaymentRequests
                )
                .build();
    }
}