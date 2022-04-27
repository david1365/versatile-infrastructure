package ir.caspco.versatile.rest.client.common.esb.payment.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class PaymentParameterVO {
    private String foreignTitle;
    private String id;
    private String title;
    private String value;
}
