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
public class CompanyPlanShortInfoVO {

    private Long id;
    private String planDescription;
    private BigDecimal loanAmount;
    private Integer loanDurationYear;
    private Integer loanDurationMonth;
    private Integer loanDurationDayPartial;
    private Double interestRate;
    private String repaymentType;
}
