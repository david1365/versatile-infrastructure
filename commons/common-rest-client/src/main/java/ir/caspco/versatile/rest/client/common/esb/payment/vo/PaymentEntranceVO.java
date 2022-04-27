package ir.caspco.versatile.rest.client.common.esb.payment.vo;

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
public class PaymentEntranceVO {
    public static final String defaultChannelType = "MOBILE_BANK";
    public static final Boolean defaultRequireVerification = false;
    public static final String defaultPaymentServiceId = "302";


    private BigDecimal amount;
    private CardAuthorizeParamsVO cardAuthorizeParams;
    private String channelType;
    private String pan;
    private String paymentServiceId;
    private String trackingNo;
    private Boolean requireVerification;
}
