package ir.caspco.versatile.jms.client.common.msg.hampa;

import ir.caspco.versatile.common.validation.annotations.IsValidNationalCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class InquireCreditCardRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @IsValidNationalCode
    private String nationalCode;
}
