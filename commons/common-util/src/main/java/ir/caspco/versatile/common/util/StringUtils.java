package ir.caspco.versatile.common.util;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public class StringUtils {

    public static String miniaturize(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

}
