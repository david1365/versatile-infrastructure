package ir.caspco.versatile.common.util.swagger;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class SwaggerUtil {

    private static final PathMatcher pathMatcher = new AntPathMatcher();


    private static final String CSS_SWAGGER_UI_PATH = "/swagger-ui/swagger-ui.css**";

    public static final String[] SWAGGER_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };

    public static boolean match(String path) {

        for (String swaggerPath : SWAGGER_WHITELIST) {

            if (pathMatcher.match(swaggerPath, path)) {
                return true;
            }
        }

        return false;
    }

    public static String[] swaggerWhitelist(String contextPath, String[] whitelistPaths) {

        String[] whitelist = new String[SWAGGER_WHITELIST.length * whitelistPaths.length];

        int i = 0;
        for (String path : whitelistPaths) {

            for (String swaggerPath : SWAGGER_WHITELIST) {

                whitelist[i] = contextPath + path + swaggerPath;

                i++;
            }
        }

        return whitelist;
    }

    public static boolean isCssSwaggerUiPath(String path) {

        return pathMatcher.match(CSS_SWAGGER_UI_PATH, path);
    }
}
