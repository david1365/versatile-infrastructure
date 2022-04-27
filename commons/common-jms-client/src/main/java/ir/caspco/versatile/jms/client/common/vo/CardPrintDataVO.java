package ir.caspco.versatile.jms.client.common.vo;

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
public class CardPrintDataVO {

    private String cardNumber;
    private String persianName;
    private String englishName;
    private String cvv2;
    private String issueDate;
    private String persianExpireDate;
    private String gregorianExpireDate;
    private String iban;
    private String itemTopic;
    private CardTypeVO cardTypeDto;
    private String extraInfo;
    private String persianFirstName;
    private String persianLastName;
    private String englishFirstName;
    private String englishLastName;
}
