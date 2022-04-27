package ir.caspco.versatile.common.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
class CronTest {

    @Test
    void nextTimeAfter() {

        Calendar calendar = Calendar.getInstance();

        Date now = calendar.getTime();

        calendar.add(Calendar.SECOND, 2);
        Date expectedDate = calendar.getTime();

        String cronString = "*/2 * * * * ?";
        Date actualDate = Cron.expression(cronString).nextValidTimeAfter(now);

        assertEquals(expectedDate.toString(), actualDate.toString());

        Date actualInvalidTimeAfter = Cron.expression(cronString).nextInvalidTimeAfter(now);

        assertEquals(expectedDate.toString(), actualInvalidTimeAfter.toString());

        Cron.expression(cronString).nextValidTimeAfterNow();
    }

    @Test
    void isValidExpression() {

        assertTrue(Cron.isValidExpression("* * * * * ?"));

        assertFalse(Cron.isValidExpression("* * * * ?"));
    }
}