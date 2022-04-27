package ir.caspco.versatile.jms.client.common.vo.hampa;

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
public class TranInqVO {
    private String clientTrackingCode;
    private String serverTrackingCode;
    private String businessTrackingCode;
    private Boolean checkUniqueTrackingCode;
    private String channel;
    private Integer rowUpdSeq;
}
