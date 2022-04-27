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
public class ThirdPartyConfigVO {

    private Long id;
    private String title;
    private String contractNumber;
    private String accountNumber;
    private String depositProduct;
    private Long defaultCustomer;
    private String branchCode;
    private BigDecimal fixAmount;
    private Long cutOfID;
    private Boolean isActive;
}