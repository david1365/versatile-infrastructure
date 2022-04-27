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
public class PaymentInformationVO implements Serializable {
    private String accountRef;
    private Boolean isCash;
    private BigDecimal amount;
    private Long signersRow;
    private Boolean freeCharge;
    private Date ChequeDate;
    private Long ChequeNumber;
    private String neginAccNumber;
    private Integer paymentType;
}
