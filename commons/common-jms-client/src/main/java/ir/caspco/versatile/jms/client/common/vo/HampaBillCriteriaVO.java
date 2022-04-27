package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class HampaBillCriteriaVO {

    private String segmentCode;
    private String paymentNumber;
    private String billNumber;
    private Long customerNumber;
    private String cardNumber;
    private String accountNumber;
    private Integer trackingNumber;
    private PaymentInformationVO paymentInformation;
    private String rrn;
    private String channelType;
}
