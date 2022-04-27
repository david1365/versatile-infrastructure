package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.annotations.IsValidUUID;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckIsValidUUIDValidator implements ConstraintValidator<IsValidUUID, String> {

    private static final String UUIDValidationRegex = "^[{]?[0-9a-fA-F]{8}"
            + "-([0-9a-fA-F]{4}-)"
            + "{3}[0-9a-fA-F]{12}[}]?$";


    @Override
    public boolean isValid(String UUIDAsString, ConstraintValidatorContext constraintValidatorContext) {

        if (UUIDAsString == null) {
            return true;
        }

        if (!StringUtils.hasText(UUIDAsString)) {
            return false;
        }

        return UUIDAsString.matches(UUIDValidationRegex);

    }

}
