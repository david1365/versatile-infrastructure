package ir.caspco.versatile.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class FileUtil {

    public static boolean exists(String path) {

        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        URL url = defaultClassLoader != null ? defaultClassLoader.getResource(path) : ClassLoader.getSystemResource(path);

        return url != null;
    }

    public static String classpath(String path) {

        return String.format("%s:%s", "classpath", path);
    }


    public static String readFileFromResource(String path) throws IOException {

        ClassPathResource resource = new ClassPathResource(path);

        InputStream inputStream = resource.getInputStream();

        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
