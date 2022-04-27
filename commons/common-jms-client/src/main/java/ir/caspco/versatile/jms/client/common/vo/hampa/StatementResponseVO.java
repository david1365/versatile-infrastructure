package ir.caspco.versatile.jms.client.common.vo.hampa;

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
public class StatementResponseVO {

    private String agentBranchCode;
    private String agentBranchName;
    private BigDecimal balance;
    private String branchCode;
    private String branchName;
    private Date date;
    private String description;
    private String referenceNumber;
    private Long registrationNumber;
    private String chequeNo;
    private String receiptNo;
    private BigDecimal transferAmount;
    private Integer sequence;
    private String dealDescription;
}
