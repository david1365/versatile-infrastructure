package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.ChangeBillToLoanRequest;
import ir.caspco.versatile.jms.client.common.msg.ChangeBillToLoanResponse;
import ir.caspco.versatile.jms.client.common.vo.ChangeToLoanVO;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "creditCard.message.ChangBillToLoanRequestMsg")
public class ChangeBillToLoanClient extends JmsClient<ChangeBillToLoanRequest, ChangeBillToLoanResponse> {

    public boolean changeBillToLoan(ChangeToLoanVO changeToLoan) {

        ChangeBillToLoanRequest creditLoanDetailRequest = ChangeBillToLoanRequest.builder()
                .changeToLoanRequest(changeToLoan)
                .build();

        ChangeBillToLoanResponse getCreditLoanDetailResponse = send(creditLoanDetailRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);

        return true;
    }
}
