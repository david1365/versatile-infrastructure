package ir.caspco.versatile.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@PropertySource(value = "file:${override.home}/override.properties")
public class GlobalConfiguration {
}
