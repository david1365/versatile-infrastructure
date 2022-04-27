package ir.caspco.versatile.validation;

import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.context.validation.annotation.Groups;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@Slf4j
public class DefaultValidator implements DValidator {

    private final Validator validator;
    private final ExecutableValidator executableValidator;


    public DefaultValidator(Validator validator) {

        this.validator = validator;
        this.executableValidator = validator.forExecutables();

    }


    @Override
    public void validate(Object target, Class<?>... groups) {

        log.info("Starting Validation .....");

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);

        checkValidation(target, constraintViolations);

        log.info("Done Validation .....");

    }

    @Override
    public void validateParameters(Object target, Method method, Object... arguments) {

        Class<?>[] groups = groups(target, method);

        validateParameters(target, method, arguments, groups);

    }

    @Override
    public void validateReturnValue(Object target, Method method, Object result) {

        Class<?>[] groups = groups(target, method);

        validateReturnValue(target, method, result, groups);

    }

    private Class<?>[] groups(Object target, Method method) {

        return method.isAnnotationPresent(Groups.class) ?
                method.getAnnotation(Groups.class).value() :

                method.isAnnotationPresent(Validated.class) ?
                        method.getAnnotation(Validated.class).value() :

                        target.getClass().isAnnotationPresent(Groups.class) ?
                                target.getClass().getAnnotation(Groups.class).value() :

                                target.getClass().isAnnotationPresent(Validated.class) ?
                                        target.getClass().getAnnotation(Validated.class).value() :

                                        new Class<?>[0];

    }

    private void checkValidation(Object target, Set<ConstraintViolation<Object>> constraintViolations) {

        if (constraintViolations.size() > 0) {
            throw new ValidationException(target, constraintViolations);
        }

    }

    private void validateReturnValue(Object target, Method method, Object result, Class<?>[] groups) {

        log.info("Starting Return Value Validation .....");

        Set<ConstraintViolation<Object>> constraintViolations =
                executableValidator.validateReturnValue(target, method, result, groups);

        checkValidation(target, constraintViolations);

        log.info("Done Return Value Validation .....");

    }

    private void validateParameters(Object target, Method method, Object[] arguments, Class<?>[] groups) {

        log.info("Starting Parameters Validation .....");

        Set<ConstraintViolation<Object>> constraintViolations =
                executableValidator.validateParameters(target, method, arguments, groups);

        checkValidation(target, constraintViolations);

        log.info("Done Parameters Validation .....");

    }

}
