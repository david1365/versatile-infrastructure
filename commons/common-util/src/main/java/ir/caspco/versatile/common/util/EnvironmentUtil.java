package ir.caspco.versatile.common.util;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class EnvironmentUtil {

    private final Environment environment;


    public EnvironmentUtil(Environment environment) {
        this.environment = environment;
    }


    public String getProperty(String key) {

        String value = environment.getProperty(key);

        return value != null ? value : key;

    }
}
