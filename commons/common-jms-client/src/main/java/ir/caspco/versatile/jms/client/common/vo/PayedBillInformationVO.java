package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
public final class PayedBillInformationVO implements Serializable {

    private Integer trackingNumber;
    private String reference;
    private Date paymentDate;
    private String channelType;
    private BigDecimal billAmount;
    private String billId;
    private String billType;
    private String billPayment;
    private String billTitle;

}

