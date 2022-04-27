package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.cash.samle.Samp0Field;
import ir.caspco.versatile.common.util.reflect.cash.samle.Samp1Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

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
        SampleCash.class,
        SampleListCash.class,
        Samp0Field.class,
        Samp1Field.class
})
class MethodCashTest {

    @Autowired
    SampleCash sampleCash;

    @Autowired
    SampleListCash sampleListCash;


    @Test
    void invoke() {

        String actual = (String) sampleCash.invoke(NullPointerException.class);

        assertEquals(SampleCash.sampleString, actual);

    }

    @Test
    void throwException() {
        try {
            sampleCash.invoke(Exception.class);
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchMethodException);
        }
    }

    @Test
    void invokeList() {
        List<Object> expected = new ArrayList<>();
        expected.add("k1, k2");
        expected.add("k2, k3");

        List<Object> actual = sampleListCash.invoke("k2");

        assertEquals(expected, actual);

    }

    @Test
    void throwExceptionList() {
        try {
            sampleListCash.invoke("k4");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchMethodException);
        }
    }
}