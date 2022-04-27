package ir.caspco.versatile.context.handler.exceptions;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public class ReturnTypeException extends GatewayException {
    public ReturnTypeException() {
    }

    public ReturnTypeException(int resultNumber) {
        super(resultNumber);
    }

    public ReturnTypeException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public ReturnTypeException(Object... args) {
        super(args);
    }

    public ReturnTypeException(String message) {
        super(message);
    }

    public ReturnTypeException(String message, Object... args) {
        super(message, args);
    }

    public ReturnTypeException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public ReturnTypeException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public ReturnTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
