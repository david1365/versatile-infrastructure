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
public class CollectPaymentRequestVO {

    private String cardNumber;
    private String segmentCode;//هما سگمنت otr
    private BigDecimal amount;// بهتر است همه مبلغ باشد.مبلغ به اندازه تسویه
    private String externalRef;//شماره حساب مثلا برای همپا . حسابی که پول از آن برداشت می شود
    private String clientTrackingCode;
}
