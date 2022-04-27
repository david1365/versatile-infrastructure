package ir.caspco.versatile.context.jms.client.exceptions;

import ir.caspco.versatile.context.jms.client.model.FaultDetails;
import lombok.Getter;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Getter
public class CoreException extends RuntimeException {
    private FaultDetails faultDetails;

    public CoreException() {
    }

    public CoreException(FaultDetails faultDetails) {

        this(faultDetails.getMessages().isEmpty() ? "CoreException!" : faultDetails.getMessages().get(0));

        this.faultDetails = faultDetails;

    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

    public CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
