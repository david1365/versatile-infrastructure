package ir.caspco.versatile.context.handler.exceptions;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class VersatileException extends GatewayException {

    public VersatileException() {
    }

    public VersatileException(int resultNumber) {
        super(resultNumber);
    }

    public VersatileException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public VersatileException(Object... args) {
        super(args);
    }

    public VersatileException(String message) {
        super(message);
    }

    public VersatileException(String message, Object... args) {
        super(message, args);
    }

    public VersatileException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public VersatileException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public VersatileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
