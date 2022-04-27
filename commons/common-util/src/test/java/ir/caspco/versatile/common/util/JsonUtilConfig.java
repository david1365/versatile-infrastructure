package ir.caspco.versatile.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
public class JsonUtilConfig {
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
