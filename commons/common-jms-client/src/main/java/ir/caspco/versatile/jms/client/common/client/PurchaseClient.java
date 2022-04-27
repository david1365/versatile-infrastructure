package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.common.util.result.Result;
import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.msg.PurchaseRequest;
import ir.caspco.versatile.jms.client.common.msg.PurchaseResponse;
import ir.caspco.versatile.jms.client.common.vo.PurchaseRequestVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "payment.billPayment.message.BillAndThirdPartyPaymentForWallet")
public class PurchaseClient extends JmsClient<PurchaseRequest, PurchaseResponse> {

    @SneakyThrows
    public Result<PurchaseResponse> purchase(PurchaseRequestVO purchaseRequestVO) {

        PurchaseRequest purchaseRequest = PurchaseRequest.builder()
                .billAndThirdPartyWalletRequest(purchaseRequestVO)
                .build();


        return send(purchaseRequest);

    }

}
