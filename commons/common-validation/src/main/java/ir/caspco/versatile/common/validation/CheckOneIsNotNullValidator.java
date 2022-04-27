package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import ir.caspco.versatile.common.validation.annotations.OneIsFull;
import ir.caspco.versatile.common.validation.annotations.OneIsNotNull;

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

public class CheckOneIsNotNullValidator implements ConstraintValidator<OneIsNotNull, Object> {

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {

        List<Optional<?>> fieldValues = ReflectUtil.fieldValues(target, OneIsFull.class);

        return fieldValues.stream().anyMatch(Optional::isPresent);

    }

}
