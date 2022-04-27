package ir.caspco.versatile.handler.exceptions.message.factory;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.ForExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ForExceptionHandler(DataIntegrityViolationException.class)
public class DataIntegrityViolationExceptionCheckModule implements CheckModule {

    @Override
    public Boolean check(Throwable error, String key) {

        return Arrays.stream(error.getCause().getStackTrace())
                .anyMatch(stackTraceElement -> stackTraceElement.getClassName().contains(key));

    }

}
