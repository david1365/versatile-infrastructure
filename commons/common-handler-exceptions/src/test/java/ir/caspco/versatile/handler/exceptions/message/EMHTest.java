package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.context.handler.exceptions.message.ErrorDescription;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class EMHTest implements ErrorMessageHandler<Object> {

    @Override
    public Object message(Throwable error, List<ErrorDescription> descriptions) {
        return null;
    }
}
