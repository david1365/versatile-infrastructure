package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.RevertTransactionEntranceVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevertTranRequest {

    @JsonProperty("revertTransactionRequestDto")
    private RevertTransactionEntranceVO revertTransactionEntranceVO;
}
