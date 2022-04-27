package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.jms.client.JmsClientConfiguration;
import ir.caspco.versatile.jms.client.common.msg.GetMultiSegmentBillInfoForCollectRequest;
import ir.caspco.versatile.jms.client.common.msg.GetMultiSegmentBillInfoForCollectResponse;
import ir.caspco.versatile.jms.client.common.vo.BillCollectionInfoVO;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentRequestVO;
import ir.caspco.versatile.jms.client.context.JmsClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        GetMultiSegmentBillInfoForCollectClient.class
})
@TestPropertySource("classpath:jms-client-test.properties")
public class GetMultiSegmentBillInfoForCollectClientTest {

    @Autowired
    private GetMultiSegmentBillInfoForCollectClient getMultiSegmentBillInfoForCollectClient;

    @Test
    void creditBillsDirect() {
        GetMultiSegmentBillInfoForCollectRequest getMultiSegmentBillInfoForCollectRequest =
                GetMultiSegmentBillInfoForCollectRequest.builder()
                        .collectPaymentRequest(
                                CollectPaymentRequestVO.builder()
                                        .cardNumber("6221061210999738")
                                        .build()
                        )
                        .build();

        GetMultiSegmentBillInfoForCollectResponse getMultiSegmentBillInfoForCollectResponse =
                getMultiSegmentBillInfoForCollectClient.send(getMultiSegmentBillInfoForCollectRequest)
                        .retrieve()
                        .result()
                        .orElse(null);

        assertNotNull(getMultiSegmentBillInfoForCollectResponse);
    }

    @Test
    void creditBills() {

        List<BillCollectionInfoVO> billCollectionInfos = getMultiSegmentBillInfoForCollectClient.creditBills("6221061210999738");

        assertNotNull(billCollectionInfos);
        assertFalse(billCollectionInfos.isEmpty());
    }
}