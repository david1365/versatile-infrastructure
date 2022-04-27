package ir.caspco.versatile.security.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.util.Collections;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {

    @Value("${security.oauth2.client.user-name-attribute}")
    private String userNameAttribute;

    private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    @Override
    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String username = (String) convertedClaims.get(userNameAttribute);
        convertedClaims.put("sub", username);
        return convertedClaims;
    }

}
