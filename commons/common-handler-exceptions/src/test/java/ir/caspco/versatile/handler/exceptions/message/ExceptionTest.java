package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import ir.caspco.versatile.context.handler.exceptions.annotations.ErrorId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@ErrorId(110110)
public class ExceptionTest extends GatewayException {
}
