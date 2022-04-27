package ir.caspco.versatile.jms.client.common.vo;

import ir.caspco.versatile.jms.client.common.enums.loan.LendingFileStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class LoanInfoVO {

    private String lotusAccountId;
    private String currency;
    private String previousSystemAccountId;
    private Long loanFileNumber;
    private String previousSystemLoanFileNumber;
    private Long loanMemoNumber;
    private String contractNumber;

    private FacilityVO facility;
    private GeneralParameterVO contract;
    private BranchVO branch;
    private AccountVO connectedAccount;
    private GeneralParameterVO status;
    private LendingFileStatus loanStatus;
    private GeneralParameterVO committeeResultType;

    private Boolean partnershipFlag;
    private Boolean installmentalFlag;

    private Date approvalDate;
    private Date firstGrantDate;
    private Date contractDueDate;
    private Date firstDueDate;
    private Date nextDueDate;
    private Date settlementDate;

    private Double interestRate;
    private Double commissionRate;
    private Integer loanDurationDay;

    private BigDecimal cashPreReceipt;

    private BigDecimal loanAmount;
    private BigDecimal principalRemain;
    private BigDecimal totalInterestAmount;
    private BigDecimal repaymentUpToNow;

    private BigDecimal totalDebitBalance;
    private BigDecimal overdueDebitBalance;
    private BigDecimal undueDebitBalance;

    private Integer installmentCount;

    private Integer repaidInstallmentCount;
    private BigDecimal repaidInstallmentAmount;

    private Integer unRepaidInstallmentCount;
    private BigDecimal unRepaidInstallmentAmount;

    private Integer unRepaidOverdueInstallmentCount;
    private BigDecimal unRepaidOverdueInstallmentAmount;

    private BigDecimal totalPenaltyAmount;
    private BigDecimal repaidPenaltyUpToNow;
    private BigDecimal unRepaidPenaltyUpToNow;

    private Integer totalDelayedInDay;

    private BigDecimal repaidChargeUpToNow;
    private BigDecimal chargeBalance;

    private BigDecimal totalDiscountAmount;
    private BigDecimal discountOfEarlyRepayment;

    private BigDecimal currentDueBalance;
    private BigDecimal pastDueBalance;
    private BigDecimal arrearsBalance;
    private BigDecimal repaymentSuspiciousBalance;

    private BigDecimal futureInterestBalance;

    private List<CustomerVO> customers;
    private List<CustomerVO> guarantors;
    private List<GuarantorInfoVO> guarantorInfos;
    private List<InstallmentInformationVO> repaymentTable;
}
