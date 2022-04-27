package ir.caspco.versatile.jms.client.common.msg.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.vo.hampa.TranInqEntranceVO;
import lombok.Builder;
import lombok.Data;

/**
 * @author sadeghi
 * date:1400/08/30
 * TransactionInquiryService
 */
@Data
@Builder
public class TranInqRequest {

    @JsonProperty("transactionInqueryRequestDto")
    private TranInqEntranceVO tranInqEntranceVO;
}
