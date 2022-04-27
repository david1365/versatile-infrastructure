package ir.caspco.versatile.jms.client.common.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum StatementSearchDirection {

    START_TO_END("ASC"),
    END_TO_START("DESC");

    private final String value;

    StatementSearchDirection(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatementSearchDirection fromValue(String v) {

        for (StatementSearchDirection direction : StatementSearchDirection.values()) {

            if (direction.value.equals(v)) {
                return direction;
            }
        }

        throw new IllegalArgumentException(v);
    }
}
