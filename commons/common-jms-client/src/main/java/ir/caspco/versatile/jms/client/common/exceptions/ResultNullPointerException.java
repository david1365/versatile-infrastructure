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
public class ResultNullPointerException extends GatewayException {
    public ResultNullPointerException() {
    }

    public ResultNullPointerException(int resultNumber) {
        super(resultNumber);
    }

    public ResultNullPointerException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public ResultNullPointerException(Object... args) {
        super(args);
    }

    public ResultNullPointerException(String message) {
        super(message);
    }

    public ResultNullPointerException(String message, Object... args) {
        super(message, args);
    }

    public ResultNullPointerException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public ResultNullPointerException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public ResultNullPointerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
