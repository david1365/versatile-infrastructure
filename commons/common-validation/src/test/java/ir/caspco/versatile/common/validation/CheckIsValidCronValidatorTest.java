package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.classes.CronClass;
import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import ir.caspco.versatile.validation.DefaultValidator;
import ir.caspco.versatile.validation.configuration.ValidatorConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ValidatorConfiguration.class,
        DefaultValidator.class,
        CronClass.class
})
class CheckIsValidCronValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    CronClass cronClass;

    @Test
    void isValid() throws NoSuchMethodException {

        Method cron = CronClass.class.getMethod("cron", String.class);

        try {

            dValidator.validateParameters(cronClass, cron, "** * *");

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);
        }

    }

    @Test
    void isValidCorrect() throws NoSuchMethodException {

        Method cron = CronClass.class.getMethod("cron", String.class);

        dValidator.validateParameters(cronClass, cron, "* * * * * ?");

    }
}