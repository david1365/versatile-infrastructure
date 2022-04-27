package ir.caspco.versatile.jms.client.common.msg;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class PayLoanResponse {

    private BigDecimal appliedAmount;
    private String documentNumber;
    private String documentTitle;
    private Date paymentTimeStamp;
}