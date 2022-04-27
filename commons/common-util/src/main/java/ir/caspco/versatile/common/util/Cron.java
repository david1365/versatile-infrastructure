package ir.caspco.versatile.common.util;

import ir.caspco.versatile.context.exceptions.CronParseException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Slf4j
public class Cron {

    private final String expression;

    public static Cron expression(String expression) {
        return new Cron(expression);
    }

    private Cron(String expression) {
        this.expression = expression;
    }

    public Date nextValidTimeAfter(Date date) {

        return parse().getNextValidTimeAfter(date);
    }

    public Date nextInvalidTimeAfter(Date date) {

        return parse().getNextInvalidTimeAfter(date);
    }

    public Date nextValidTimeAfterNow() {
        return nextValidTimeAfter(new Date());
    }

    public static boolean isValidExpression(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    public Date nextInvalidTimeAfterNow() {

        return nextInvalidTimeAfter(new Date());
    }

    private CronExpression parse() {

        try {

            return new CronExpression(expression);
        } catch (ParseException e) {

            log.error("ParseException: ", e);
            throw new CronParseException();
        }
    }
}
