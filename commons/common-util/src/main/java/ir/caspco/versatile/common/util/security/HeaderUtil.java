package ir.caspco.versatile.common.util.security;

import ir.caspco.versatile.context.enums.TokenType;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public class HeaderUtil {

    public static String basic(String username, String password) {

        String auth = username + ":" + password;

        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));

        return TokenType.Basic.name() + " " + new String(encodedAuth);

    }

    public static boolean isExpired(Date expireDate) {

        if (expireDate == null) {
            return true;
        }

        long now = new Date().getTime();

        return expireDate.getTime() < now;

    }

    public static List<String> resolveNamedValues(String name, ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().get(name);
    }

    public static String resolveNamedValue(String name, ServerWebExchange exchange) {

        List<String> headerValues = resolveNamedValues(name, exchange);
        String result = null;

        if (headerValues != null) {
            result = (headerValues.size() >= 1 ? headerValues.get(0) : null);
        }

        return result;

    }

}
