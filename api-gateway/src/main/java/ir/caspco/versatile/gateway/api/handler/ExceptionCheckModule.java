package ir.caspco.versatile.gateway.api.handler;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.ForExceptionHandler;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ForExceptionHandler(Exception.class)
public class ExceptionCheckModule implements CheckModule {

    @Override
    public Boolean check(Throwable error, String key) {
        return true;
    }

}
