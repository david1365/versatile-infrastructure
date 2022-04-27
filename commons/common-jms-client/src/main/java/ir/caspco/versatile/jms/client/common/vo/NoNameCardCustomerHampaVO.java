package ir.caspco.versatile.jms.client.common.vo;

import ir.caspco.versatile.common.validation.annotations.IsValidMobileNumber;
import ir.caspco.versatile.common.validation.annotations.IsValidNationalCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class NoNameCardCustomerHampaVO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String fullName;

    @NotNull
    @NotBlank
    @NotEmpty
    @IsValidNationalCode
    private String nationalId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String fullAddress;

    @NotNull
    @NotBlank
    @NotEmpty
    private String addressType;


    private Long version;

    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;

    @NotNull
    @NotBlank
    @NotEmpty
    private Character gender;

    @NotNull
    @NotBlank
    @NotEmpty
    @IsValidMobileNumber
    private String mobileTel;

    @NotNull
    @NotBlank
    @NotEmpty
    private String cityName;

    @NotNull
    @NotBlank
    @NotEmpty
    private String province;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 10, max = 10)
    private String postalCode;

    @NotNull
    @NotBlank
    @NotEmpty
    private String identityNo;

    @NotNull
    @NotBlank
    @NotEmpty
    private String identitySerial;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 1, max = 6)
    private String identitySerialPart1;

    @NotNull
    @NotBlank
    @NotEmpty
    private String identitySerialPart2;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 1, max = 2)
    private String identitySerialPart3;

    @NotNull
    @NotBlank
    @NotEmpty
    private String fatherName;

    @NotNull
    private Date birthDate;

    @NotNull
    @NotBlank
    @NotEmpty
    private String birthPlace;

    @NotNull
    @NotBlank
    @NotEmpty
    private String issuePlace;
}
