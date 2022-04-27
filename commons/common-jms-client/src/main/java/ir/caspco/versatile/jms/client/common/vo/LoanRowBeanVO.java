package ir.caspco.versatile.jms.client.common.vo;

import ir.caspco.versatile.jms.client.common.enums.loan.PayStatus;
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
public class LoanRowBeanVO {

    private Integer delayDay;
    private Date payDate;
    private PayStatus payStatus;
    private BigDecimal payedAmount;
    private BigDecimal penaltyAmount;
    private BigDecimal unpaidAmount;
}
