package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import ir.caspco.versatile.context.handler.exceptions.annotations.MessagesPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
public class MessageConfiguration {

    private final ApplicationContext applicationContext;

    public MessageConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public MessageSource messageSource() {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(MessagesPath.class);

        String[] baseNames = beanMap.values().stream()
                .map(mpBean -> ReflectUtil.realClassObject(mpBean.getClass()).getAnnotation(MessagesPath.class).value())
                .flatMap(Stream::of)
                .toArray(String[]::new);


        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasenames(baseNames);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
