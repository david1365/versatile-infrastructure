package ir.caspco.versatile.common.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoreUtilTest {

    @Test
    void getTrackingNumber() {

        Integer actual = CoreUtil.generateTrackingNumber(BigDecimal.valueOf(10));

        Integer expected = 10;

        assertEquals(expected, actual);


        actual = CoreUtil.generateTrackingNumber(BigDecimal.valueOf(66666666));

        expected = 666666;

        assertEquals(expected, actual);
    }
}