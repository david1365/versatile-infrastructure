package ir.caspco.versatile.context.handler.exceptions.cash;

import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface MessageHandlerCash {
    ErrorMessageHandler<?> getProvider(Throwable error);
}
