package ir.caspco.versatile.context.handler.exceptions.message;

import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import ir.caspco.versatile.context.handler.exceptions.annotations.ErrorId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface ErrorMessageHandler<M> {

    M message(Throwable error, List<ErrorDescription> descriptions);

    default HttpStatus status(Throwable error) {
        final int resultNumberLength = 3;
        final Class<?> errorClass = error.getClass();

        Integer resultNumber = getResultNumber(error);
        if (resultNumber != null) {

            String strResultNumber = resultNumber.toString();
            final boolean lengthIs4 = strResultNumber.length() >= (resultNumberLength + 1);
            final boolean lengthIs3 = strResultNumber.length() >= resultNumberLength;

            if (lengthIs4 || lengthIs3) {

                int start = 0;
                int end = resultNumberLength;

                if (lengthIs4) {
                    if (strResultNumber.startsWith("1")) {
                        start++;
                        end++;
                    }
                }

                HttpStatus status = HttpStatus.resolve(Integer.parseInt(strResultNumber.substring(start, end)));

                if (status != null) {
                    return status;
                }

            }

        }

        return errorClass.isAnnotationPresent(ResponseStatus.class) ?
                errorClass.getAnnotation(ResponseStatus.class).value() : HttpStatus.BAD_REQUEST;

    }

    default int errorId(Throwable error) {

        final Class<?> errorClass = error.getClass();

        Integer resultNumber = getResultNumber(error);
        if (resultNumber != null) {
            return resultNumber;
        }

        return errorClass.isAnnotationPresent(ErrorId.class) ?
                errorClass.getAnnotation(ErrorId.class).value() : HttpStatus.BAD_REQUEST.value();

    }

    default Integer getResultNumber(Throwable error) {

        if (error instanceof GatewayException) {

            final GatewayException gatewayException = ((GatewayException) error);
            if (gatewayException.getResultNumber() != null) {
                return gatewayException.getResultNumber();
            }

        }

        return null;

    }

}
