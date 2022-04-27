package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.util.Cron;
import ir.caspco.versatile.common.validation.annotations.IsValidCron;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckIsValidCronValidator implements ConstraintValidator<IsValidCron, String> {

    @Override
    public boolean isValid(String cronExpression, ConstraintValidatorContext constraintValidatorContext) {

        if (cronExpression == null) {
            return true;
        }

        if (!StringUtils.hasText(cronExpression)) {
            return false;
        }

        return Cron.isValidExpression(cronExpression);

    }
}
