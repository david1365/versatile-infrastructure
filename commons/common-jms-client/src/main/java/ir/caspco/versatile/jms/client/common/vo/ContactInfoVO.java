package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class ContactInfoVO {

    private long id;
    private long version;
    private long entityType;
    private long entityID;
    private String contactType;
    private String contactValue;
    private Date startDate;
    private Date expireDate;
    private long branchCode;
    private boolean isDefault;
    private Date insertTime;
    private String insertUser;
}
