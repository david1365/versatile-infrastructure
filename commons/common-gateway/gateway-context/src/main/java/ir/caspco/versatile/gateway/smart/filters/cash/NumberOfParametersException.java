package ir.caspco.versatile.gateway.smart.filters.cash;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public class NumberOfParametersException extends RuntimeException {
    public NumberOfParametersException() {
    }

    public NumberOfParametersException(String message) {
        super(message);
    }

    public NumberOfParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfParametersException(Throwable cause) {
        super(cause);
    }

    public NumberOfParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
