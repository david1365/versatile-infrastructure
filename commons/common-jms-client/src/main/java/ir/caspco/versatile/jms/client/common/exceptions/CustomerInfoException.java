package ir.caspco.versatile.jms.client.common.exceptions;

import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CustomerInfoException extends GatewayException {
    public CustomerInfoException() {
    }

    public CustomerInfoException(int resultNumber) {
        super(resultNumber);
    }

    public CustomerInfoException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public CustomerInfoException(Object... args) {
        super(args);
    }

    public CustomerInfoException(String message) {
        super(message);
    }

    public CustomerInfoException(String message, Object... args) {
        super(message, args);
    }

    public CustomerInfoException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public CustomerInfoException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public CustomerInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
