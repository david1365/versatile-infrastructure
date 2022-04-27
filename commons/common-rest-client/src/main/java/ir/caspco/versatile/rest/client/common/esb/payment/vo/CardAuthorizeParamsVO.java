package ir.caspco.versatile.rest.client.common.esb.payment.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class CardAuthorizeParamsVO {
    public static final String defaultPinType = "EPAY";

    private String cvv2;
    private String expDate;
    private String pin;
    private String pinType;
}
