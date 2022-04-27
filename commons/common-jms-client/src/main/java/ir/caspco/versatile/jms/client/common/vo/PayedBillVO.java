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
public class PayedBillVO {

    private Long id;
    private BillConfigCutOfVO billConfigCutOfDto;
    private String paymentNumber;
    private String billNumber;
    private String branchCode;
    private String channelType;
    private Long customerNumber;
    private Integer trackingNumber;
    private Date paymentDate;
    private BigDecimal billAmount;
    private BillConfigVO billConfigDto;
    private Boolean isReversed;
    private Long version;

}
