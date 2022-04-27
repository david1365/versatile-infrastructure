package ir.caspco.versatile.context.common.util.result;

import lombok.Getter;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Getter
public class ResultErrorException extends RuntimeException {

    Throwable target;

    public ResultErrorException() {
    }

    public ResultErrorException(String message) {
        super(message);
    }

    public ResultErrorException(Throwable target) {
        this.target = target;
    }

    public ResultErrorException(String message, Throwable target) {
        super(message);
        this.target = target;
    }

    public ResultErrorException(String message, Throwable cause, Throwable target) {
        super(message, cause);
        this.target = target;
    }

    public ResultErrorException(Throwable cause, Throwable target) {
        super(cause);
        this.target = target;
    }

    public ResultErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Throwable target) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.target = target;
    }

    public ResultErrorException target(Throwable target) {
        this.target = target;

        return this;
    }

}
