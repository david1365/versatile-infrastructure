package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class CollectPaymentResponseVO {

    private String segmentCode;
    private BigDecimal principleAmount;
    private BigDecimal interestAmount;//اگر این دو مبلغ بخشی از مبلغ کل باشد محاسبه می شود و در دوفیلا مبالغی قرار می گیرد.
    private String dealReference;
    private String requestTrackingNumber;
}
