package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.annotations.IsValidMobileNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckIsValidMobileNumberValidator implements ConstraintValidator<IsValidMobileNumber, String> {

    private static final String mobileNumberValidationRegex = "^(((\\+{1})|(0{2}))98|(0{1}))9[0-9]{1}\\d{8}\\Z$";


    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (mobileNumber == null) {
            return true;
        }

        if (!StringUtils.hasText(mobileNumber)) {
            return false;
        }

        return mobileNumber.matches(mobileNumberValidationRegex);

    }

}
