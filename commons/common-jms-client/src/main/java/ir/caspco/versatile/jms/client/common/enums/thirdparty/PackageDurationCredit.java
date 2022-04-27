package ir.caspco.versatile.jms.client.common.enums.thirdparty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum PackageDurationCredit {

    ONE_DAY(1),
    THREE_DAY(3),
    ONE_WEEK(7),
    ONE_MONTH(30),
    TWO_MONTH(60),
    THREE_MONTH(90),
    FOUR_MONTH(120),
    SIX_MONTH(180),
    NINE_MONTH(270),
    ONE_YEARLY(365);

    private final int value;
    private static final Map<Integer, PackageDurationCredit> MAP = new HashMap<>();

    static {

        for (PackageDurationCredit enOpackageDurationCrediteratorEnum : PackageDurationCredit.values()) {

            MAP.put(enOpackageDurationCrediteratorEnum.value(), enOpackageDurationCrediteratorEnum);
        }
    }

    public int value() {

        return this.value;
    }


    PackageDurationCredit(int value) {

        this.value = value;
    }


    public static PackageDurationCredit ValueOf(int value) {

        return MAP.get(value);
    }
}