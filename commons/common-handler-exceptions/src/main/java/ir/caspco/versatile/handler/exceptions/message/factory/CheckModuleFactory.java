package ir.caspco.versatile.handler.exceptions.message.factory;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import ir.caspco.versatile.context.handler.exceptions.annotations.ForExceptionHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Component
public class CheckModuleFactory implements InitializingBean {

    private final ApplicationContext applicationContext;
    private final CheckModule defaultCheckModule;

    private final Map<Class<? extends Throwable>, CheckModule> classCheckModuleMap = new ConcurrentHashMap<>();


    public CheckModuleFactory(ApplicationContext applicationContext,
                              @Qualifier("defaultCheckModule") CheckModule defaultCheckModule) {

        this.applicationContext = applicationContext;
        this.defaultCheckModule = defaultCheckModule;

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, CheckModule> checkModuleMap = applicationContext.getBeansOfType(CheckModule.class);

        checkModuleMap.values().forEach(checkModule -> {

            Class<?> checkModuleClass = checkModule.getClass();
            if (checkModuleClass.isAnnotationPresent(ForExceptionHandler.class)) {

                Class<? extends Throwable> errorClass = checkModuleClass.getAnnotation(ForExceptionHandler.class).value();
                classCheckModuleMap.put(errorClass, checkModule);

            }

        });

    }

    public CheckModule checkModule(Class<? extends Throwable> error) {

        Optional<CheckModule> checkModule = classCheckModuleMap.keySet().stream()
                .filter(errorClass -> errorClass.isAssignableFrom(error))
                .map(classCheckModuleMap::get)
                .findFirst();

        return checkModule.orElse(defaultCheckModule);

    }

}
