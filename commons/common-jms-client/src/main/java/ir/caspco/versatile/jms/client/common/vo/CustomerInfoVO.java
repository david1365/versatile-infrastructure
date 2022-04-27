package ir.caspco.versatile.jms.client.common.vo;

import ir.caspco.versatile.jms.client.common.enums.ChGender;
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
public class CustomerInfoVO {
    private static final long serialVersionUID = -5445080759910017470L;

    private String cif;
    private Character clientType;
    private String nationality;
    private String nationalityTitle;
    private String code;
    private String Shahabcode;
    private String foreignerNo;
    private ChGender gender;
    private String genderTitle;
    private String name;
    private String title;
    private String firstName;
    private String lastName;
    private String foreignName;
    private String englishFirstName;
    private String englishLastName;
    private String fatherName;
    private String identityNo;
    private String birthPlace;
    private String birthPlaceTitle;
    private Date birthDate;
    private String issuePlace;
    private String issuePlaceTilte;
    private String registrationCode;
    private String economicCode;
    private String foundationPlace;
    private String foundationPlaceTitle;
    private Date foundationDate;
    private Integer employeeCount;
    private String tradeCardId;
    private Date tradeCardExpireDate;
    private String corporateType;
    private String occupation;    //economic section
    private Character enable;
    private String postalCode;
    private String address;
}
