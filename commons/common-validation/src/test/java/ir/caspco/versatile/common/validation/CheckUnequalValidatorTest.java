package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.unequal.UnequalSampleClass;
import ir.caspco.versatile.common.validation.unequal.UnequalSampleModel;
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
        UnequalSampleClass.class
})
class CheckUnequalValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    UnequalSampleClass unequalSampleClass;

    @Test
    void isNull() throws NoSuchMethodException {

        Method type = UnequalSampleClass.class.getMethod("type", UnequalSampleModel.class);

        try {

            dValidator.validateParameters(unequalSampleClass, type, UnequalSampleModel.builder().build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);
        }
    }

    @Test
    void isEquals() throws NoSuchMethodException {

        Method type = UnequalSampleClass.class.getMethod("type", UnequalSampleModel.class);

        try {

            dValidator.validateParameters(unequalSampleClass, type,
                    UnequalSampleModel.builder()
                            .integer1(1)
                            .integer2(1)
                            .integer3(1)
                            .build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);
        }
    }

    @Test
    void isUnequal() throws NoSuchMethodException {

        Method type = UnequalSampleClass.class.getMethod("type", UnequalSampleModel.class);

        dValidator.validateParameters(unequalSampleClass, type,
                UnequalSampleModel.builder()
                        .integer1(1)
                        .integer2(2)
                        .integer3(3)
                        .build());
    }
}