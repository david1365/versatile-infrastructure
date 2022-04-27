package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.BillPaymentByHampaCardResponseVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BillPaymentByHampaCardResponse {

    @JsonProperty("billPaymentByHampaCardResponseDto")
    private BillPaymentByHampaCardResponseVO billPaymentByHampaCardResponseVO;
}
