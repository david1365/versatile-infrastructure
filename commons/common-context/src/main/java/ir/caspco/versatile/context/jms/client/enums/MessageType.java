package ir.caspco.versatile.context.jms.client.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum MessageType {
    REQUEST(100),
    RESPONSE(200),
    NOTIFICATION(300);

    private final Integer code;

    MessageType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
