package ir.caspco.versatile.jms.client.common.exceptions;

import ir.caspco.versatile.context.handler.exceptions.VersatileException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CoreContentResultException extends VersatileException {

    public CoreContentResultException() {
    }

    public CoreContentResultException(int resultNumber) {
        super(resultNumber);
    }

    public CoreContentResultException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public CoreContentResultException(Object... args) {
        super(args);
    }

    public CoreContentResultException(String message) {
        super(message);
    }

    public CoreContentResultException(String message, Object... args) {
        super(message, args);
    }

    public CoreContentResultException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public CoreContentResultException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public CoreContentResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
