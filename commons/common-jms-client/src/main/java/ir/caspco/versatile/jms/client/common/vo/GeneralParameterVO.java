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
public class GeneralParameterVO {

    private Long id;
    private Integer version;
    private Long parentId;
    private String code;
    private String shortTitle;
    private String longTitle;
    private String mainGroup;
    private Boolean isActive = true;
    private Short childOrder;

    private String equivalentCode;
    private BigDecimal parameterValue;

    private GeneralParameterVO parent;
}
