package ir.caspco.versatile.jms.client.common.client;

import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.JmsClient;
import ir.caspco.versatile.jms.client.common.exceptions.CoreContentResultException;
import ir.caspco.versatile.jms.client.common.msg.LoadCompanyPlanRequest;
import ir.caspco.versatile.jms.client.common.msg.LoadCompanyPlanResponse;
import ir.caspco.versatile.jms.client.common.vo.CompanyPlanRequestVO;
import ir.caspco.versatile.jms.client.common.vo.SegmentCompanyPlanVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@JmsHeader(serviceId = "creditcard.message.LoadCompanyPlanMsg")
public class LoadCompanyPlanClient extends JmsClient<LoadCompanyPlanRequest, LoadCompanyPlanResponse> {

    public List<SegmentCompanyPlanVO> loadCompanyPlan(CompanyPlanRequestVO companyPlan) {

        LoadCompanyPlanRequest companyPlanRequest = LoadCompanyPlanRequest.builder()
                .companyPlan(companyPlan)
                .build();

        return send(companyPlanRequest)
                .retrieve()
                .result()
                .orElseThrow(CoreContentResultException::new)
                .getSegmentCompanyPlanList();
    }
}
