package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.GReflect;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public abstract class BaseMethodCash<K, V, CA extends Annotation, MA extends Annotation, O> implements InitializingBean, GReflect, BaseCash<K, V> {

    private final ApplicationContext applicationContext;


    abstract protected void initPut(MethodInformation methodInformation, CA classAnnotation, MA methodAnnotation);

    abstract protected O invoke(K key, Object... args);

    public BaseMethodCash(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() {

        Class<? extends Annotation> classAnnotation = (Class<? extends Annotation>) genericClassObjects()[1];
        Class<? extends Annotation> methodAnnotation = (Class<? extends Annotation>) genericClassObjects()[2];

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(classAnnotation);

        beanMap.values().stream()
                .filter(this::filter)
                .forEach(object -> {
                    List<Method> methods = MethodUtils.getMethodsListWithAnnotation(object.getClass(), methodAnnotation);

                    methods.forEach(method ->
                            initPut(
                                    MethodInformation.builder()
                                            .method(method)
                                            .object(object)
                                            .build(),
                                    (CA) object.getClass().getAnnotation(classAnnotation),
                                    (MA) method.getAnnotation(methodAnnotation)
                            )
                    );

                });

    }

}
