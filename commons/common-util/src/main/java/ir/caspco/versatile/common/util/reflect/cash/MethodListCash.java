package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public abstract class MethodListCash<K, CA extends Annotation, MA extends Annotation> extends BaseMethodCash<K, List<MethodInformation>, CA, MA, List<Object>> {

    private final Map<K, List<MethodInformation>> kvMap = new ConcurrentHashMap<>();


    public MethodListCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }


    @Override
    public final Map<K, List<MethodInformation>> kvMap() {
        return kvMap;
    }

    public final void put(K key, MethodInformation value) {

        List<MethodInformation> methodInformations = kvMap.get(key);
        if (methodInformations == null) {
            methodInformations = new ArrayList<>();
        }

        methodInformations.add(value);
        kvMap.put(key, methodInformations);

    }

    @SneakyThrows
    public List<Object> invoke(K key, Object... args) {

        List<MethodInformation> methodInformations = get(key).orElseThrow(NoSuchMethodException::new);

        if (methodInformations.isEmpty()) {
            throw new NoSuchMethodException(key instanceof String ? key.toString() : key.getClass().getSimpleName());
        }

        return methodInformations.stream()
                .map(methodInfo -> ReflectUtil.invoke(methodInfo.getMethod(), methodInfo.getObject(), args))
                .collect(Collectors.toList());

    }

}
