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
public class CollectPaymentVO {

    private Long id;
    private String status;
    private Long fileId;
    private BigDecimal interestDiff;
    private Date calcDate;
    private BigDecimal principleRemainAmount;
    private BigDecimal interestRemainAmount;
    private BigDecimal totalCollectAmount;
    private BigDecimal principleCollectShare;
    private BigDecimal interestCollectShare;
    private Date fromDate;
    private Date toDate;
}
