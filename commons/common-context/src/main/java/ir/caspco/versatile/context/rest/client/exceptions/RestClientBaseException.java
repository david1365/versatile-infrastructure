package ir.caspco.versatile.context.rest.client.exceptions;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class RestClientBaseException extends RuntimeException {
    public RestClientBaseException() {
    }

    public RestClientBaseException(String message) {
        super(message);
    }

    public RestClientBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestClientBaseException(Throwable cause) {
        super(cause);
    }

    public RestClientBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
