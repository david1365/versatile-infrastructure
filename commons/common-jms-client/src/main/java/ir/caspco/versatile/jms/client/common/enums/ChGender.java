package ir.caspco.versatile.jms.client.common.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum ChGender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    LEGAL("LEGAL"),
    PUBLIC("PUBLIC");

    private final String value;

    private ChGender(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
