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
public class PayedBill {

    private Long id;
    private BillConfigCutOf billConfigCutOf;
    private String paymentNumber;
    private String billNumber;
    private String branchCode;
    private String channelType;
    private Long customerNumber;
    private String accountNumber;
    private String cardNumber;
    private Integer trackingNumber;
    private Date paymentDate;
    private BigDecimal billAmount;
    private BillConfig billConfig;
    private Boolean isReversed;
    private String reference;
    private Long version;
    private String rrn;

}
