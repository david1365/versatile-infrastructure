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
public class BillCollectionInfoVO {

    private String fileNo;//شماره کارت اعتباری )ID(
    private String segmentCode;
    private Date fromDate;
    private Date toDate;
    private BigDecimal principleRemainAmount;//اصل بدهی
    private BigDecimal interestRemainAmount;//مبلغ سود بدهی
    private BigDecimal collectableAmount;//جمع اصل بهدی و سود بدهی
}
