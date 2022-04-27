package ir.caspco.versatile.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
public class CommonUtilConfiguration {

    @Bean
    public PathMatcher antPathMatcher() {

        return new AntPathMatcher();
    }
}
