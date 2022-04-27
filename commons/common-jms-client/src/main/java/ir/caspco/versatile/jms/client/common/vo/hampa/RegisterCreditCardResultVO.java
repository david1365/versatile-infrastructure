package ir.caspco.versatile.jms.client.common.vo.hampa;

import ir.caspco.versatile.jms.client.common.enums.FlowLevel;
import ir.caspco.versatile.jms.client.common.enums.FlowStatus;
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
public class RegisterCreditCardResultVO {

    private String nationalCode;
    private String clientTrackingCode;
    private Long customerNumber;
    private String shahabCode;
    private String accountNumber;
    private String facilityCode;
    private BigDecimal loanAmount;
    private BigDecimal totalAmount;
    private Integer loanDurationYear;
    private Integer loanDurationMonth;
    private Integer loanDurationDayPartial;
    private Long creditCardRepaymentTypeId;
    private String macnaCode;
    private Long traceCD;
    private Long loanFileNumber;
    private Long collateralCode;
    private String cardNumber;
    private FlowLevel currentLevel;
    private FlowStatus currentStatus;
    private String exceptionCode;
    private String exceptionArgs;

    private Boolean completed;

    private String creditAccountNumber;
    private Boolean mainAccount;
    private Boolean status;
    private String segmentCode;

    public Boolean isCompleted() {
        return completed;
    }
}
