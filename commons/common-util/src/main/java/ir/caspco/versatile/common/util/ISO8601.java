package ir.caspco.versatile.common.util;

import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public class ISO8601 {

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");


    static {

        DATE_FORMAT.setTimeZone(TIME_ZONE);

    }

    public static String toISO8601UTC(Date date) {

        return DATE_FORMAT.format(date);

    }

    @SneakyThrows
    public static Date fromISO8601UTC(String dateStr) {

        return DATE_FORMAT.parse(dateStr);

    }

}
