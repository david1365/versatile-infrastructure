package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.CashOutHampaCardRequestVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class CashOutHampaCardByAccountRequest {

    @JsonProperty("cashOutHampaCardRequestDto")
    private CashOutHampaCardRequestVO cashOutHampaCardRequest;

}