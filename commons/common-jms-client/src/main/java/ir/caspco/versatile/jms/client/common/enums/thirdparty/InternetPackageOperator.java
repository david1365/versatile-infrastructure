package ir.caspco.versatile.jms.client.common.enums.thirdparty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum InternetPackageOperator {

    MCI(5),
    IRANCELL(6),
    RIGHTEL(7),
    TALIA(8);

    private final int value;
    private static Map<Integer, InternetPackageOperator> map = new HashMap<>();

    static {

        for (InternetPackageOperator enOperatorEnum : InternetPackageOperator.values()) {

            map.put(enOperatorEnum.value(), enOperatorEnum);
        }
    }

    public int value() {
        return this.value;
    }


    InternetPackageOperator(int value) {
        this.value = value;
    }


    public static InternetPackageOperator ValueOf(int value) {

        return map.get(value);
    }
}