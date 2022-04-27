package ir.caspco.versatile.context.jms.client.exceptions;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public class ClientConfigException extends RuntimeException {
    public ClientConfigException() {
        super("The object is not configured!");
    }
}
