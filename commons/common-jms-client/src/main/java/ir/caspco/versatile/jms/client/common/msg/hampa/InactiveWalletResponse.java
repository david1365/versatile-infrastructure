package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.InactiveWalletResponseVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class InactiveWalletResponse {

    @JsonProperty("responseDto")
    private InactiveWalletResponseVO inactiveWalletResponseVO;
}
