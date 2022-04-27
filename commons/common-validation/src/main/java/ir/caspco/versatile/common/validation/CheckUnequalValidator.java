package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import ir.caspco.versatile.common.validation.annotations.ThisIsUnequal;
import ir.caspco.versatile.common.validation.annotations.Unequal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckUnequalValidator implements ConstraintValidator<Unequal, Object> {

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {

        List<Optional<?>> fieldValues = ReflectUtil.fieldValues(target, ThisIsUnequal.class);

        long filledCount = fieldValues.stream().filter(Optional::isPresent).count();

        if (filledCount < fieldValues.size() - 1) {
            return false;
        }

        return fieldValues.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .allMatch(firstFieldValue ->
                        filledCount - 1 == fieldValues.stream()
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .filter(secondFieldValue -> !secondFieldValue.equals(firstFieldValue))
                                .count());

    }
}
