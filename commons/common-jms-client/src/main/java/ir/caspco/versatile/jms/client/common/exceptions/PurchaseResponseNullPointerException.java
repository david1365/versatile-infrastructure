package ir.caspco.versatile.jms.client.common.exceptions;


import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PurchaseResponseNullPointerException extends GatewayException {
}