package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.common.util.reflect.cash.ObjectCash;
import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.MessageHandler;
import ir.caspco.versatile.context.handler.exceptions.cash.MessageHandlerCash;
import ir.caspco.versatile.context.handler.exceptions.message.DefaultMessage;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;
import ir.caspco.versatile.handler.exceptions.message.factory.CheckModuleFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Component
public class DefaultMessageHandlerCash extends ObjectCash<String, ErrorMessageHandler<?>, MessageHandler> implements MessageHandlerCash {

    private final ErrorMessageHandler<?> defaultErrorMessageHandler;
    private final CheckModuleFactory checkModuleFactory;


    public DefaultMessageHandlerCash(ApplicationContext applicationContext,
                                     CheckModuleFactory checkModuleFactory,
                                     @Qualifier("defaultErrorMessageHandler")
                                             ErrorMessageHandler<DefaultMessage> defaultErrorMessageHandler) {

        super(applicationContext);

        this.checkModuleFactory = checkModuleFactory;
        this.defaultErrorMessageHandler = defaultErrorMessageHandler;

    }

    @Override
    protected void initPut(ErrorMessageHandler<?> errorMessageHandler, MessageHandler messageHandler) {
        kvMap().put(messageHandler.value(), errorMessageHandler);
    }


    @Override
    public ErrorMessageHandler<?> getProvider(Throwable error) {

        Optional<? extends ErrorMessageHandler<?>> errorMessageHandler = kvMap().keySet().stream()
                .filter(key -> {

                    CheckModule checkModule = checkModuleFactory.checkModule(error.getClass());
                    return checkModule.check(error, key);

                })
                .map(key -> kvMap().get(key))
                .findFirst();

        if (errorMessageHandler.isPresent()) {
            return errorMessageHandler.get();
        }

        return defaultErrorMessageHandler;

    }
}
