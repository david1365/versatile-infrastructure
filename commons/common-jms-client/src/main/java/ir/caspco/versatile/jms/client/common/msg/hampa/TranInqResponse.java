package ir.caspco.versatile.jms.client.common.msg.hampa;


import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.TranInqResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author sadeghi
 * date:1400/08/30
 * TransactionInquiryService
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TranInqResponse {

    @JsonProperty("responseDto")
    private TranInqResultVO tranInqResult;
}
