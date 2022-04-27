package ir.caspco.versatile.context.jms.client.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum RequestType {
    TRANSACTION(100),
    INQUIRY(200);

    private final Integer code;

    private RequestType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}

