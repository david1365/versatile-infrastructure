package ir.caspco.versatile.handler.exceptions.message.factory;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.ForExceptionHandler;
import org.springframework.core.MethodParameter;
import org.springframework.web.server.ServerWebInputException;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ForExceptionHandler(ServerWebInputException.class)
public class ServerWebInputExceptionCheckModule implements CheckModule {

    @Override
    public Boolean check(Throwable error, String key) {

        MethodParameter methodParameter = ((ServerWebInputException) error).getMethodParameter();
        return methodParameter != null && methodParameter.getDeclaringClass().getPackage().getName().contains(key);

    }

}
