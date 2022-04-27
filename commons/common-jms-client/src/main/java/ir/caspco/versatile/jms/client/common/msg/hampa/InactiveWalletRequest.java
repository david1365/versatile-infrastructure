package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.InactiveWalletEntranceVO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class InactiveWalletRequest {

    @JsonProperty("requestDto")
    private InactiveWalletEntranceVO inactiveWalletEntranceVO;
}
