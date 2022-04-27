package ir.caspco.versatile.jms.client.common.vo.hampa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author sadeghi
 * date:1400/08/30
 * TransactionInquiryService
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class TranInqResultVO {

    @JsonProperty("hampaTransactionInquiryDtoList")
    private List<TranInqVO> tranInqResult;
}
