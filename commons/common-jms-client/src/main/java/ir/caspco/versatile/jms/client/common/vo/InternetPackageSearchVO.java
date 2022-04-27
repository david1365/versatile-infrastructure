package ir.caspco.versatile.jms.client.common.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.InternetPackageOperator;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.PackageType;
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
public class InternetPackageSearchVO {

    @JsonProperty("packageTypeEnum")
    private PackageType packageType;

    @JsonProperty("internetPackageOperatorEnum")
    private InternetPackageOperator operator;

    private Integer durationCredit;

    private BigDecimal fromAmount;
    private BigDecimal toAmount;
}
