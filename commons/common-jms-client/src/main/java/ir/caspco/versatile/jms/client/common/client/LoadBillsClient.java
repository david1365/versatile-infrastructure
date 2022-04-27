package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.LoadBillsRequest;
import ir.caspco.versatile.jms.client.common.msg.LoadBillsResponse;
import ir.caspco.versatile.jms.client.common.vo.CreditCardBillVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "creditcard.message.LoadBillsMsg")
public class LoadBillsClient extends JmsClient<LoadBillsRequest, LoadBillsResponse> {

    public List<CreditCardBillVO> loadBills(String cardNumber) {

        LoadBillsRequest loadBillsRequest = LoadBillsRequest.builder()
                .cardNumber(cardNumber)
                .build();

        return send(loadBillsRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new)
                .getBills();
    }
}
