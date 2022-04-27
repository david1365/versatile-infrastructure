package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.classes.UUIDClass;
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
        UUIDClass.class
})
class CheckIsValidUUIDValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    UUIDClass uuidClass;


    @Test
    void isValid() throws NoSuchMethodException {

        Method UUID = UUIDClass.class.getMethod("UUID", String.class);

        try {

            dValidator.validateParameters(uuidClass, UUID, "ce53d042b-7de9-4e5f-a780-6c985db72035");

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidCorrect() throws NoSuchMethodException {

        Method UUID = UUIDClass.class.getMethod("UUID", String.class);

        dValidator.validateParameters(uuidClass, UUID, java.util.UUID.randomUUID().toString());

    }

    private void equals(ValidationException e) {
        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actual = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.classes.UUIDClass.UUID.arg0").get(0);

        assertEquals("ir.caspco.versatile.common.validation.annotations.IsValidUUID.message", actual);
    }

}