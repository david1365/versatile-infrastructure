package ir.caspco.versatile.gateway.api.handler;

import ir.caspco.versatile.context.handler.exceptions.annotations.MessageHandler;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorDescription;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Qualifier("apiGatewayErrorMessageHandler")
@MessageHandler("ir.caspco.versatile.gateway.api")
public class ApiGatewayErrorMessageHandler implements ErrorMessageHandler<List<ErrorDescription>> {

    @Override
    public List<ErrorDescription> message(Throwable error, List<ErrorDescription> descriptions) {

        return descriptions;

    }

}
