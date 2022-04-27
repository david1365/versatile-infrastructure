package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.PurchaseThirdPartyResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 * @author Mohammad Javad Zahedi - 2021
 * mjavadzahedi0@gmail.com
 * 09377732642
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class PurchaseThirdPartyResponse {

    @JsonProperty("buyThirdPartyByHampaCardResponseDto")
    private PurchaseThirdPartyResultVO purchaseThirdPartyResult;
}
