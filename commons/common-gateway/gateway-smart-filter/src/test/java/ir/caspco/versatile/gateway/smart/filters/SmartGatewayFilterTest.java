package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.common.util.EnvironmentUtil;
import ir.caspco.versatile.common.util.MyProperties;
import ir.caspco.versatile.gateway.smart.filters.cash.CashBuilder;
import ir.caspco.versatile.gateway.smart.filters.util.HeaderUtil;
import ir.caspco.versatile.validation.DefaultValidator;
import ir.caspco.versatile.validation.configuration.ValidatorConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        EnvironmentUtil.class,
        DefaultMethodInfo.class,
        PrefixTest.class,
        WithoutPrefixTest.class,
        CashBuilder.class,
        HeaderUtil.class,
        MyProperties.class,
        DefaultValidator.class,
        ValidatorConfiguration.class
})
class SmartGatewayFilterTest {

    @Autowired
    private PrefixTest prefixTest;

    @Autowired
    private WithoutPrefixTest withoutPrefixTest;

    @Test
    void prefix() {
        String expected = "/prefix";

        assertEquals(expected, prefixTest.prefix());

        prefixTest.init("/basePrefix");

        assertEquals(expected, prefixTest.prefix());
    }

    @Test
    void withoutPrefix() {
        String expected = "/basePrefix";

        assertEquals("", withoutPrefixTest.prefix());

        withoutPrefixTest.init(expected);

        assertEquals(expected, withoutPrefixTest.prefix());
    }

    @Test
    void path() {
        String expected = "/path";

        assertEquals(expected, prefixTest.path());

        assertEquals("", withoutPrefixTest.path());
    }

    @Test
    void order() {
        int expected = 123;

        assertEquals(expected, prefixTest.order());

        assertEquals(-2, withoutPrefixTest.order());
    }
}