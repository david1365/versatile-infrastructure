package ir.caspco.versatile.jms.client.common.vo;

import ir.caspco.versatile.jms.client.common.enums.loan.LoanPaymentMethod;
import lombok.Builder;
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
public class LoanPaymentVO {

    private String cif;
    private BigDecimal amount;
    private String customDepositNumber;
    private String loanNumber;

    @Builder.Default
    private LoanPaymentMethod paymentMethod = LoanPaymentMethod.CUSTOM_DEPOSIT;

    private String secondPassword;

    @Builder.Default
    private Boolean secondPasswordNecessity = Boolean.FALSE;
}
