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
public class InternalAccountTransferResultVO {

    private String dealReference;
    private String serverTrackingCode;
    private String clientTrackingCode;
    private Date transactionDate;
    private BigDecimal currentBookBalance;
}
