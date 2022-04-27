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
public class CompleteCustomerInfoVO {
    //share
    private String customerNo;
    private String name;
    private String nationalNo;
    private String nationality;
    private String foreignName;
    private String foreignerNo;
    private String email;
    private String mobileNo;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String phoneNo;
    private String address;
    //individual
    private String family;
    private String fatherName;
    private Date referralDate;
    private String identificationNo;
    private Date birthDate;
    private String identificationCharacter;
    private String identificationLongSerial;
    private String identificationShortSerial;
    private String birthLocation;
    private String identificationIssuingLocationCode;
    private String authenticationDocuments;
    private Character gender;
    private String passportNo;
    private Date passportExpireDate;
    private Character maritalStatus;
    private Character healthStatus;
    private String foreignFamily;
    private String foreignFatherName;
    //corporate
    private String registrationCode;
}
