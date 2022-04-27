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
public class CustomerVO {

    private IndividualVO individual;
    private AddressVO[] addresses;
    private String defaultMobile;
    private ContactInfoVO[] mobiles;
    private ContactInfoVO[] contactInfos;
    private long amlDailyDebitEqAmt;
    private long amlDailyCreditEqAmt;
    private boolean isAmlEnable;
    private boolean isForeignerCustomer;
    private boolean hasValidIdentificationCode;
    private String hasToCheckShahabCode;
    private String insertUser;
    private long id;
    private long version;
    private String clientType;
    private long externalRef;
    private String firstName;
    private String lastName;
    private String unifiedFirstName;
    private String unifiedLastName;
    private Date startDate;
    private long branchCode;
    private String mainCurrency;
    private String clientStatus;
    private String englishFirstName;
    private String englishLastName;
    private String nationalCode;
    private String shahabCode;
    private String nationality;
    private String enable;
}
