package ir.caspco.versatile.context.exceptions;

import ir.caspco.versatile.context.handler.exceptions.VersatileException;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CronParseException extends VersatileException {

    public CronParseException() {
    }

    public CronParseException(int resultNumber) {
        super(resultNumber);
    }

    public CronParseException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public CronParseException(Object... args) {
        super(args);
    }

    public CronParseException(String message) {
        super(message);
    }

    public CronParseException(String message, Object... args) {
        super(message, args);
    }

    public CronParseException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public CronParseException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public CronParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
