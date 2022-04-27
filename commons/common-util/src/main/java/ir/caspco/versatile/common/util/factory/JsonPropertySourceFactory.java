package ir.caspco.versatile.common.util.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;

import static ir.caspco.versatile.common.util.MapUtil.flatMap;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class JsonPropertySourceFactory implements PropertySourceFactory {

    @Override
    @SuppressWarnings("unchecked")
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
        return new MapPropertySource("json-property", flatMap(readValue));
    }

}
