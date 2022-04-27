package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class BillDetailVO {

    private String billNumberWithOutParity;
    private String paymentNumberWithOutParity;
    private String billParity;
    private String serviceCode;
    private String dependentCompanyCode;
    private String fileNumber;
    private String paymentParity1;
    private String paymentParity2;
    private String periodCode;
    private String yearCode;
    private String amountWithoutCoefficient;
    private BigDecimal realAmount;
    private BillConfigVO billConfigDto;

}
