package ir.caspco.versatile.context.rest.client.common.esb;

import ir.caspco.versatile.context.rest.client.exceptions.RestClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EsbException extends RestClientErrorException {
}
