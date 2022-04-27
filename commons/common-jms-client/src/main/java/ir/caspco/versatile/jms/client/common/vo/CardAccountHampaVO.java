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
public class CardAccountHampaVO {

    private String accountNumber; //شماره حساب
    private Boolean mainAccount; //حساب اصلی است یا نه
    private Boolean status; //فعال و غیر فعال
    private String segmentCode; //شماره ارگان

}