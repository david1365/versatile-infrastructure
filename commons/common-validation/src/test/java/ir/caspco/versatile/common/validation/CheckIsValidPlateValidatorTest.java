package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.plate.PlateClass;
import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import ir.caspco.versatile.validation.DefaultValidator;
import ir.caspco.versatile.validation.configuration.ValidatorConfiguration;
import lombok.SneakyThrows;
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
        PlateClass.class
})
class CheckIsValidPlateValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    PlateClass plateClass;


    @Test
    @SneakyThrows
    void inCorrectAlphabet() {

        Method plateMethod = PlateClass.class.getMethod("plate", Integer.class);

        try {

            dValidator.validateParameters(plateClass, plateMethod, 123456789);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    @SneakyThrows
    void inCorrectLen() {

        Method plateMethod = PlateClass.class.getMethod("plate", Integer.class);

        try {

            dValidator.validateParameters(plateClass, plateMethod, 1);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }


    @Test
    @SneakyThrows
    void inCorrectCity() {

        Method plateMethod = PlateClass.class.getMethod("plate", Integer.class);

        try {

            dValidator.validateParameters(plateClass, plateMethod, 292417701);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    @SneakyThrows
    void isValidCorrect() {

        Method plateMethod = PlateClass.class.getMethod("plate", Integer.class);

        dValidator.validateParameters(plateClass, plateMethod, 292417722);
    }


    private void equals(ValidationException e) {
        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actual = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.plate.PlateClass.plate.arg0").get(0);

        assertEquals("ir.caspco.versatile.common.validation.annotations.IsValidPlate.message", actual);
    }

}