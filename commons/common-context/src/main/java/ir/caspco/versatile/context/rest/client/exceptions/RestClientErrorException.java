package ir.caspco.versatile.context.rest.client.exceptions;

import ir.caspco.versatile.context.common.util.result.ResultErrorException;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class RestClientErrorException extends ResultErrorException {

    public RestClientErrorException() {
    }

    public RestClientErrorException(String message) {
        super(message);
    }

    public RestClientErrorException(Throwable target) {
        super(target);
    }

    public RestClientErrorException(String message, Throwable target) {
        super(message, target);
    }

    public RestClientErrorException(String message, Throwable cause, Throwable target) {
        super(message, cause, target);
    }

    public RestClientErrorException(Throwable cause, Throwable target) {
        super(cause, target);
    }

    public RestClientErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Throwable target) {
        super(message, cause, enableSuppression, writableStackTrace, target);
    }

}
