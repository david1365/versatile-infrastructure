package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.GetMultiSegmentBillInfoForCollectRequest;
import ir.caspco.versatile.jms.client.common.msg.GetMultiSegmentBillInfoForCollectResponse;
import ir.caspco.versatile.jms.client.common.vo.BillCollectionInfoVO;
import ir.caspco.versatile.jms.client.common.vo.CollectPaymentRequestVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "creditcard.message.GetMultiSegmentBillInfoForCollectMsg")
public class GetMultiSegmentBillInfoForCollectClient extends JmsClient<
        GetMultiSegmentBillInfoForCollectRequest,
        GetMultiSegmentBillInfoForCollectResponse> {

    public List<BillCollectionInfoVO> creditBills(String cardNumber) {

        GetMultiSegmentBillInfoForCollectRequest getMultiSegmentBillInfoForCollectRequest =
                GetMultiSegmentBillInfoForCollectRequest.builder()
                        .collectPaymentRequest(
                                CollectPaymentRequestVO.builder()
                                        .cardNumber(cardNumber)
                                        .build()
                        )
                        .build();

        GetMultiSegmentBillInfoForCollectResponse getMultiSegmentBillInfoForCollectResponse = send(getMultiSegmentBillInfoForCollectRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);

        return getMultiSegmentBillInfoForCollectResponse.getBillCollectionInfos();
    }
}
