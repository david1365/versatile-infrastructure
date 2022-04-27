package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.mobile.MobileClass;
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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        MobileClass.class
})
class CheckIsValidMobileNumberValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    MobileClass mobileClass;

    @Test
    void isValidSingleDigit() throws NoSuchMethodException {

        Method setMobile = MobileClass.class.getMethod("setMobile", String.class);

        try {

            dValidator.validateParameters(mobileClass, setMobile, "586");

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidGreaterThan10() throws NoSuchMethodException {
        Method setMobile = MobileClass.class.getMethod("setMobile", String.class);

        try {

            dValidator.validateParameters(mobileClass, setMobile, "091251886942");

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }
    }

    @Test
    void isValidCorrect() throws NoSuchMethodException {

        Method setMobile = MobileClass.class.getMethod("setMobile", String.class);

        dValidator.validateParameters(mobileClass, setMobile, "09125188694");

    }


    private void equals(ValidationException e) {
        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actual = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.mobile.MobileClass.setMobile.arg0").get(0);

        assertEquals("ir.caspco.versatile.common.validation.annotations.IsValidMobileNumber.message", actual);
    }
}