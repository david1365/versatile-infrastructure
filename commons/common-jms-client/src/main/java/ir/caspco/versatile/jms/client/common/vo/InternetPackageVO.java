package ir.caspco.versatile.jms.client.common.vo;


import ir.caspco.versatile.jms.client.common.enums.thirdparty.PackageDurationCredit;
import ir.caspco.versatile.jms.client.common.enums.thirdparty.PackageType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class InternetPackageVO {

    private Short code;
    private String name;
    private InternetPackageOperatorVO operator;
    private PackageType simType;
    private Integer amount;
    private Boolean isActive;
    private PackageDurationCredit durationHours;
}
