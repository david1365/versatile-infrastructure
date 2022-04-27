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
public class IndividualVO {

    private String fatherName;
    private String unifiedFatherName;
    private Date birthDate;
    private String birthPlace;
    private String gender;
    private String residencyType;
    private String identityNo;
    private String identitySerial;
    private String identitySerialPart1;
    private String identitySerialPart2;
    private String identitySerialPart3;
    private String clientGroup;
    private String language;
    private String issuePlace;
    private String issuePlaceDistrictNo;
    private String title;
    private String englishFatherName;
    private String residencyRegion;
    private long monthlyIncome;
    private String passportID;
    private long identificationDocType;
    private boolean isSmallCorporate;
    private Date insertTime;
    private Date updateTime;
    private String updateUser;
    private String nahabIssueSeries;
    private String nahabIssueSerial;
    private CustomerVO parent;
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
