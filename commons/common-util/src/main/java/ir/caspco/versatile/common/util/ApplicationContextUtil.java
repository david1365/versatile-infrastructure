package ir.caspco.versatile.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class ApplicationContextUtil {
    private static ApplicationContext applicationContext;

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext applicationContext() {
        return applicationContext;
    }

    public static <B> B getBean(Class<B> bClass) {
        return applicationContext().getBean(bClass);
    }
}
