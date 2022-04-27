package ir.caspco.versatile.jms.client.common.msg;


import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.CompanyPlanRequestVO;
import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public final class LoadCompanyPlanRequest {

    @JsonProperty("request")
    private CompanyPlanRequestVO companyPlan;
}