package ir.caspco.versatile.handler.exceptions.message.factory;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.ForExceptionHandler;
import ir.caspco.versatile.context.validation.exception.ValidationException;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ForExceptionHandler(ValidationException.class)
public class ValidationExceptionCheckModule implements CheckModule {

    @Override
    public Boolean check(Throwable error, String key) {

        return ((ValidationException) error).target().getClass().getPackage().getName().contains(key);

    }

}
