package ir.caspco.versatile.context.exceptions;

import ir.caspco.versatile.context.handler.exceptions.VersatileException;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class DuplicateRecordException extends VersatileException {

    public DuplicateRecordException() {
    }

    public DuplicateRecordException(int resultNumber) {
        super(resultNumber);
    }

    public DuplicateRecordException(int resultNumber, Object... args) {
        super(resultNumber, args);
    }

    public DuplicateRecordException(Object... args) {
        super(args);
    }

    public DuplicateRecordException(String message) {
        super(message);
    }

    public DuplicateRecordException(String message, Object... args) {
        super(message, args);
    }

    public DuplicateRecordException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public DuplicateRecordException(Throwable cause, Object... args) {
        super(cause, args);
    }

    public DuplicateRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace, args);
    }
}
