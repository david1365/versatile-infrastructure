package ir.caspco.versatile.jms.client.common.vo.hampa;

import ir.caspco.versatile.common.validation.annotations.IsValidNationalCode;
import ir.caspco.versatile.common.validation.annotations.IsValidUUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class RegisterCreditCardEntranceVO {

    @NotNull
    @NotEmpty
    @NotBlank
    @IsValidNationalCode
    private String nationalCode;

    @NotNull
    private BigDecimal loanAmount;

    @NotNull
    private Long companyPlanID;

    @NotNull
    @NotEmpty
    @NotBlank
    @IsValidUUID
    private String clientTrackingCode;
}
