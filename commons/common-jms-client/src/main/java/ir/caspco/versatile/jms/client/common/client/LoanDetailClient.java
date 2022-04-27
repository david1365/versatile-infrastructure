package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.LoanDetailRequest;
import ir.caspco.versatile.jms.client.common.msg.LoanDetailResponse;
import ir.caspco.versatile.jms.client.common.vo.LoanDetailSearchVO;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "channelManagement.LoanDetailSearchRequestMsg")
public class LoanDetailClient extends JmsClient<LoanDetailRequest, LoanDetailResponse> {

    public LoanDetailResponse loanDetail(LoanDetailSearchVO loanDetailSearch) {

        LoanDetailRequest loanDetailRequest = LoanDetailRequest.builder()
                .loanDetailSearch(loanDetailSearch)
                .build();

        return send(loanDetailRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new);
    }
}
