package ir.caspco.versatile.common.util.reflect.cash;

import java.util.Map;
import java.util.Optional;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface BaseCash<K, V> {

    void put(K key, MethodInformation value);

    Map<K, V> kvMap();

    default boolean filter(Object object) {
        return true;
    }

    default boolean has(K key) {
        return kvMap().get(key) != null;
    }

    default Optional<V> get(K key) {
        return Optional.ofNullable(kvMap().get(key));
    }

}
