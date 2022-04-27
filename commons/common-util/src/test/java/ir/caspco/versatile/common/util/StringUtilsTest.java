package ir.caspco.versatile.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
class StringUtilsTest {

    @Test
    void miniaturize() {
        String expected = "mohammad";

        assertEquals(expected, StringUtils.miniaturize("Mohammad"));
    }

}