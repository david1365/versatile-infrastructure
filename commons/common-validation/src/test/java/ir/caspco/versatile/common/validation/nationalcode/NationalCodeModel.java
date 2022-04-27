package ir.caspco.versatile.common.validation.nationalcode;

import ir.caspco.versatile.common.validation.annotations.IsValidNationalCode;
import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class NationalCodeModel {

    @IsValidNationalCode
    private String nationalCode;

}
