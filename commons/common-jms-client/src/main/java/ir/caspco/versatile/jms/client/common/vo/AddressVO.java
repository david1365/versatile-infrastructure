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
public class AddressVO {

    private long id;
    private Date startDate;
    private Date expireDate;
    private long addressTypeID;
    private long entityID;
    private long entityType;
    private String countryCode;
    private String cityCode;
    private String province;
    private String fullAddress;
    private String postalCode;
    private long branchCode;
    private long version;
    private String insertUser;
    private Date insertTime;
    private ContactInfoVO[] telephones;
    private ContactInfoVO[] faxes;
    private ContactInfoVO[] contactInfos;
}
