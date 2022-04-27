package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.nationalcode.NationalCodeClass;
import ir.caspco.versatile.common.validation.nationalcode.NationalCodeModel;
import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import ir.caspco.versatile.validation.DefaultValidator;
import ir.caspco.versatile.validation.configuration.ValidatorConfiguration;
import org.apache.commons.lang3.StringUtils;
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
        NationalCodeClass.class
})
class CheckIsValidNationalCodeValidatorTest {

    @Autowired
    DValidator dValidator;

    @Autowired
    NationalCodeClass nationalCodeClass;


    @Test
    void isValidSingleDigit() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        try {

            dValidator.validateParameters(nationalCodeClass, nationalCode, NationalCodeModel.builder().nationalCode("1").build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidGreaterThan10() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        try {

            dValidator.validateParameters(nationalCodeClass, nationalCode, NationalCodeModel.builder().nationalCode("03234754778").build());

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidDuplicateNumbers() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        for (Integer i = 0; i < 10; i++) {
            NationalCodeModel nationalCodeModel = NationalCodeModel.builder().nationalCode(StringUtils.repeat(i.toString(), 10)).build();
            try {

                dValidator.validateParameters(nationalCodeClass, nationalCode, nationalCodeModel);

                fail();

            } catch (Exception e) {

                assertTrue(e instanceof ValidationException);

                equals((ValidationException) e);

            }

        }

    }

    @Test
    void isValidIsDigit() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        NationalCodeModel nationalCodeModel = NationalCodeModel.builder().nationalCode("0458o5544i").build();
        try {

            dValidator.validateParameters(nationalCodeClass, nationalCode, nationalCodeModel);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidInCorrect() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        NationalCodeModel nationalCodeModel = NationalCodeModel.builder().nationalCode("7824369512").build();
        try {

            dValidator.validateParameters(nationalCodeClass, nationalCode, nationalCodeModel);

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ValidationException);

            equals((ValidationException) e);

        }

    }

    @Test
    void isValidCorrect() throws NoSuchMethodException {

        Method nationalCode = NationalCodeClass.class.getMethod("nationalCode", NationalCodeModel.class);

        NationalCodeModel nationalCodeModel = NationalCodeModel.builder().nationalCode("0323475477").build();

        dValidator.validateParameters(nationalCodeClass, nationalCode, nationalCodeModel);

    }

    private void equals(ValidationException e) {
        Map<Integer, Map<String, List<String>>> nodeViolations = e.nodeViolations();

        String actual = nodeViolations.get(-1).get("ir.caspco.versatile.common.validation.nationalcode.NationalCodeClass.nationalCode.arg0.nationalCode").get(0);

        assertEquals("ir.caspco.versatile.common.validation.annotations.IsValidNationalCode.message", actual);
    }
}