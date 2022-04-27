package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import lombok.SneakyThrows;
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

public abstract class MethodCash<K, CA extends Annotation, MA extends Annotation> extends BaseMethodCash<K, MethodInformation, CA, MA, Object> {

    private final Map<K, MethodInformation> kvMap = new ConcurrentHashMap<>();


    public MethodCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }


    public final void put(K key, MethodInformation value) {
        kvMap.put(key, value);
    }

    @Override
    public final Map<K, MethodInformation> kvMap() {
        return kvMap;
    }

    @Override
    @SneakyThrows
    public Object invoke(K key, Object... args) {

        MethodInformation methodInformation = get(key).orElseThrow(NoSuchMethodException::new);

        return ReflectUtil.smartInvoke(methodInformation.getMethod(), methodInformation.getObject(), args);

    }

}
