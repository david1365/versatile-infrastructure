package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.cash.samle.SampObject0Field;
import ir.caspco.versatile.common.util.reflect.cash.samle.SampObject1Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        SampleObjectCash.class,
        SampObject0Field.class,
        SampObject1Field.class
})
class ObjectCashTest {
    @Autowired
    SampleObjectCash sampleObjectCash;

    @Test
    void kvMap() {
        assertNotNull(sampleObjectCash.kvMap().get("ir.hampa"));

        assertNull(sampleObjectCash.kvMap().get("ir.caspco"));
    }
}