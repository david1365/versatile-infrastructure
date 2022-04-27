package ir.caspco.versatile.jms.client.common.vo;

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
public class CreditCardBillVO {

    private Long id;
    private Long clientId;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String fileNo;
    private BigDecimal creditAmount;
    private Integer gracePeriod;
    private Integer protestDeadline;
    private Integer payoutType;
    private Integer sendType;
    private String cardNo;
    private BigDecimal beginingCardBalance;
    private BigDecimal closingCardBalance;
    private BigDecimal totalAmount;
    private Date sendDate;

    private String insertUser;
    private String updateUser;
    private Date insertDate;
    private Date updateDate;
    private Long totalCount;
    private List<CreditCardTransactionVO> creditCardTransactionDTO;

    private List<CreditCardTransactionVO> transactions;
    private List<CollectPaymentVO> collectPayments;
    private Date fromDate;
    private Date toDate;
    private Date calcDate;
    private String status;
    private BigDecimal interestAmount;
    private BigDecimal principleAmount;
    private BigDecimal accrualInterest;
    private Date accrualInterestDate;
    private BigDecimal fixBillAmount;
    private BigDecimal fixInterestAmount;
    private Date sitDurationEndDate;
    private BigDecimal sitDurationInterest;
    private Date nextDueDate;
}
