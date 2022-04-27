package ir.caspco.versatile.jms.client.common.vo.hampa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @author sadeghi
 * date:1400/08/30
 * TransactionInquiryService
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TranInqEntranceVO {

    @NotEmpty
    @NotNull
    @NotBlank

    private String clientTrackingCode;

}
