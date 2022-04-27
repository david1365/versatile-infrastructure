package ir.caspco.versatile.common.util;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CoreUtil {

    public static int generateTrackingNumber(BigDecimal id) {

        final int maxSize = 6;

        String strTrackingNumber = id.toString();
        int len = strTrackingNumber.length();

        if (len > maxSize) {

            strTrackingNumber = strTrackingNumber.substring(len - maxSize);
        }

        return Integer.parseInt(strTrackingNumber);
    }
}
