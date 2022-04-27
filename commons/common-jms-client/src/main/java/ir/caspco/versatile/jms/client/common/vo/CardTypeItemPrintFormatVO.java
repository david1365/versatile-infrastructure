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
public class CardTypeItemPrintFormatVO {

    private Integer itemType;
    private String fontName;
    private BigDecimal fontSize;
    private Integer left;
    private Integer top;
    private Integer stringAlignment;
    private Integer fontStyle;
    private String itemValue;
    private String itemMask;
    private String itemValidation;
}
