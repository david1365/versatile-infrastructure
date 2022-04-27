package ir.caspco.versatile.common.util.swagger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
class SwaggerUtilTest {

    @Test
    void match() {

        assertTrue(SwaggerUtil.match("/swagger-resources/abc"));
        assertTrue(SwaggerUtil.match("/v2/api-docs"));
        assertTrue(SwaggerUtil.match("/swagger-ui/a"));
        assertTrue(SwaggerUtil.match("/swagger-ui/a/b"));

        assertFalse(SwaggerUtil.match("/card/list"));
    }

    @Test
    void gateWaySwaggerWhitelist() {

        String[] expectedWhitelist = {
                "/gateway/hampa/v2/api-docs",
                "/gateway/hampa/v3/api-docs",
                "/gateway/hampa/swagger-resources/**",
                "/gateway/hampa/swagger-ui/**",

                "/gateway/pec/v2/api-docs",
                "/gateway/pec/v3/api-docs",
                "/gateway/pec/swagger-resources/**",
                "/gateway/pec/swagger-ui/**",
        };

        String contextPath = "/gateway";
        String[] whitelistPaths = {
                "/hampa",
                "/pec"
        };

        assertArrayEquals(expectedWhitelist, SwaggerUtil.swaggerWhitelist(contextPath, whitelistPaths));


        expectedWhitelist = new String[0];
        whitelistPaths = new String[0];

        assertArrayEquals(expectedWhitelist, SwaggerUtil.swaggerWhitelist(contextPath, whitelistPaths));
    }

    @Test
    void isCssSwaggerUiPath() {

        assertTrue(SwaggerUtil.isCssSwaggerUiPath("/swagger-ui/swagger-ui.css?v=3.0.0"));
    }
}