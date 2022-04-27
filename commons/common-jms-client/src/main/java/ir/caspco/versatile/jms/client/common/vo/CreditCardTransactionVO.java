package ir.caspco.versatile.jms.client.common.vo;

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
public class CreditCardTransactionVO {

    private Long id;
    private Long fileId;
    private Integer status;
    private String dealReference;
    private BigDecimal creditAmount;
    private BigDecimal depositAmount;
    private Date transactionDate;
    private String transactionNo;
    private BigDecimal remainBalance;
    private BigDecimal totalInterest;
    private BigDecimal monthlyInterest;
    private Date calcDate;
    private Integer branch;
    private Integer fileCount;
    private Boolean calculated;
    private Long billID;
}
