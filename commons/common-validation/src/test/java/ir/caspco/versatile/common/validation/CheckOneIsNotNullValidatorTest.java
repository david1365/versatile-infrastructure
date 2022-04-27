package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.onisnull.SampleClass;
import ir.caspco.versatile.common.validation.onisnull.SampleModel;
import ir.caspco.versatile.common.validation.onisnull.SampleModelFieldParam;
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
        SampleClass.class
})
class CheckOneIsNotNullValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    SampleClass sampleClass;


    @Test
    void isValid() throws NoSuchMethodException {

        Method type = SampleClass.class.getMethod("type", SampleModel.class);

        try {

            dValidator.validateParameters(sampleClass, type, SampleModel.builder().build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }
    }

    @Test
    void isValidParam() throws NoSuchMethodException {

        Method param = SampleClass.class.getMethod("param", SampleModelFieldParam.class);

        try {

            dValidator.validateParameters(sampleClass, param, SampleModelFieldParam.builder().build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equalsParam((ValidationException) e);

        }
    }

    @Test
    void isValidCorrectParam() throws NoSuchMethodException {

        Method param = SampleClass.class.getMethod("param", SampleModelFieldParam.class);

        dValidator.validateParameters(sampleClass, param, SampleModelFieldParam.builder().integer(1).build());

    }


    @Test
    void isValidFillSampleModelFieldParam() throws NoSuchMethodException {

        Method type = SampleClass.class.getMethod("type", SampleModel.class);

        try {

            SampleModel sampleModel = SampleModel.builder()
                    .sampleModelFieldParam(SampleModelFieldParam.builder().build())
                    .build();

            dValidator.validateParameters(sampleClass, type, sampleModel);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }
    }

    @Test
    void isValidCorrect() throws NoSuchMethodException {

        Method type = SampleClass.class.getMethod("type", SampleModel.class);

        SampleModel sampleModel = SampleModel.builder()
                .integer(12)
                .sampleModelFieldParam(SampleModelFieldParam.builder().string("davood").build())
                .build();

        dValidator.validateParameters(sampleClass, type, sampleModel);

    }

    private void equalsParam(ValidationException e) {

        final String expectedMsg = "ir.caspco.versatile.common.validation.annotations.OneIsNotNull.message";

        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actualSampleModelFieldParam = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.onisnull.SampleClass.param.arg0").get(0);

        assertEquals(expectedMsg, actualSampleModelFieldParam);

    }

    private void equals(ValidationException e) {

        final String expectedMsg = "ir.caspco.versatile.common.validation.annotations.OneIsNotNull.message";

        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actualSampleModelFieldParam = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.onisnull.SampleClass.type.arg0.sampleModelFieldParam").get(0);

        String actualTypeArg0 = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.onisnull.SampleClass.type.arg0").get(0);


        assertEquals(expectedMsg, actualSampleModelFieldParam);

        assertEquals(expectedMsg, actualTypeArg0);

    }

}