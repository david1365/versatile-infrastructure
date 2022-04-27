package ir.caspco.versatile.validation;

import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import ir.caspco.versatile.validation.configuration.ValidatorConfiguration;
import ir.caspco.versatile.validation.model.ValidationModel;
import ir.caspco.versatile.validation.vtest.ValidationClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ValidationClass.class
})
class DefaultValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    ValidationClass validationClass;

    @Test
    void validateParameters() throws NoSuchMethodException {

        Method m1 = ValidationClass.class.getMethod("m1", ValidationModel.class);

        Object[] params = new Object[1];
        params[0] = null;

        try {
            dValidator.validateParameters(validationClass, m1, params);
        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            Map<Integer, Map<String, List<String>>> nodeViolations = ((ValidationException) e).nodeViolations();

            String actual = nodeViolations.get(-1).get("ir.caspco.versatile.validation.vtest.ValidationClass.m1.arg0").get(0);

            assertEquals("javax.validation.constraints.NotNull.message", actual);

        }

    }

    @Test
    void validateFieldParameters() throws NoSuchMethodException {

        Method m1 = ValidationClass.class.getMethod("m1", ValidationModel.class);

        Object[] params = new Object[1];
        params[0] = ValidationModel.builder().build();

        try {
            dValidator.validateParameters(validationClass, m1, params);
        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            Map<Integer, Map<String, List<String>>> nodeViolations = ((ValidationException) e).nodeViolations();

            String actualName = nodeViolations.get(-1).get("ir.caspco.versatile.validation.vtest.ValidationClass.m1.arg0.name").get(0);
            String actualFamily = nodeViolations.get(-1).get("ir.caspco.versatile.validation.vtest.ValidationClass.m1.arg0.family").get(0);

            assertEquals("javax.validation.constraints.NotNull.message", actualName);
            assertEquals("ir.caspco.family", actualFamily);

        }

    }

    @Test
    void validateReturnValue() throws NoSuchMethodException {

        Method m1 = ValidationClass.class.getMethod("m1", ValidationModel.class);

        String result = validationClass.m1(
                ValidationModel.builder()
                        .name("davood")
                        .family("akbari")
                        .build()
        );

        try {
            dValidator.validateReturnValue(validationClass, m1, result);
        } catch (Exception e) {
            assertTrue(e instanceof ValidationException);
        }

    }
}