package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.InsertMultiSegmentCollectPaymentRequest;
import ir.caspco.versatile.jms.client.common.msg.InsertMultiSegmentCollectPaymentResponse;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentRequestVO;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentResponseVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "creditcard.message.InsertMultiSegmentCollectPaymentMsg")
public class InsertMultiSegmentCollectPaymentClient extends JmsClient<
        InsertMultiSegmentCollectPaymentRequest,
        InsertMultiSegmentCollectPaymentResponse> {

    public List<CollectPaymentResponseVO> creditBillPayment(List<CollectPaymentRequestVO> collectPaymentRequests) {

        InsertMultiSegmentCollectPaymentRequest insertMultiSegmentCollectPaymentRequest =
                InsertMultiSegmentCollectPaymentRequest.builder()
                        .collectPaymentRequests(
                                collectPaymentRequests
                        )
                        .build();

        InsertMultiSegmentCollectPaymentResponse insertMultiSegmentCollectPaymentResponse = send(insertMultiSegmentCollectPaymentRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);

        return insertMultiSegmentCollectPaymentResponse.getCollectPaymentResponses();
    }
}
