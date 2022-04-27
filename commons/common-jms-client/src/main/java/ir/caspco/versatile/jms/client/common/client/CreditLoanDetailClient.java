package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.CreditLoanDetailRequest;
import ir.caspco.versatile.jms.client.common.msg.CreditLoanDetailResponse;
import ir.caspco.versatile.jms.client.common.vo.LoanInfoVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "lending.creditCard.GetCreditLoanDetailMsg")
public class CreditLoanDetailClient extends JmsClient<CreditLoanDetailRequest, CreditLoanDetailResponse> {

    public List<LoanInfoVO> creditLoanDetail(String cardNumber) {

        CreditLoanDetailRequest creditLoanDetailRequest = CreditLoanDetailRequest.builder()
                .cardNumber(cardNumber)
                .build();

        CreditLoanDetailResponse getCreditLoanDetailResponse = send(creditLoanDetailRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);

        return getCreditLoanDetailResponse.getLoansInformation();
    }
}
