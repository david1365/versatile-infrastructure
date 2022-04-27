package ir.caspco.versatile.rest.client.common.esb.payment.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@NoArgsConstructor
public class PaymentResultVo {
    private List<PaymentParameterVO> paymentParameters;
    private String trackingNumber;

    private Date transactionDate;
}
