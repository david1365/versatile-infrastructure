package ir.caspco.versatile.context.validation;

import java.lang.reflect.Method;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface DValidator {

    void validate(Object target, Class<?>... groups);

    void validateParameters(Object target, Method method, Object... arguments);

    void validateReturnValue(Object target, Method method, Object result);

}
