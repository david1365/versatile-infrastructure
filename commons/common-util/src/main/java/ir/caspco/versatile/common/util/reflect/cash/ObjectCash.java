package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.GReflect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public abstract class ObjectCash<K, V, CA extends Annotation> implements InitializingBean, GReflect, BaseCash<K, V> {

    private final Map<K, V> kvMap = new ConcurrentHashMap<>();

    private final ApplicationContext applicationContext;


    abstract protected void initPut(V object, CA classAnnotation);


    public ObjectCash(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() {

        Class<? extends Annotation> classAnnotation = (Class<? extends Annotation>) genericClassObjects()[2];

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(classAnnotation);

        beanMap.values().stream()
                .filter(this::filter)
                .forEach(object ->

                        initPut(
                                (V) object,
                                (CA) object.getClass().getAnnotation(classAnnotation)
                        )

                );

    }

    @Override
    @SuppressWarnings("unchecked")
    public void put(K key, MethodInformation value) {
        kvMap.put(key, (V) value.getObject());
    }

    @Override
    public Map<K, V> kvMap() {
        return kvMap;
    }
}
