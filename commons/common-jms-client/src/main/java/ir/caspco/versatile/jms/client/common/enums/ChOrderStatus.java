package ir.caspco.versatile.jms.client.common.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum ChOrderStatus {

    DESC,
    ASC;

    public String value() {
        return name();
    }

    public static ChOrderStatus fromValue(String v) {
        return valueOf(v);
    }
}
