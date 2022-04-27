package ir.caspco.versatile.jms.client.common.vo;


import ir.caspco.versatile.jms.client.common.enums.loan.PayStatus;
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
public class LoanDetailSearchVO {

    private String loanNumber;
    private String cbLoanNumber;

    private boolean hasDetail;
    private PayStatus payStatus;

    private Long offset;
    private Long length;
}
