package ir.caspco.versatile.gateway.smart.filters.cash;



import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public class DCash<T> implements Cash<T> {

    private final Map<String, T> map;
    private final String key;


    DCash(Map<String, T> map, String key) {
        this.map = map;
        this.key = key;
    }

    @Override
    public void put(T target) {

        map.put(key, target);

    }

    @Override
    public T get() {

        T target = map.get(key);
        remove();

        return target;

    }

    @Override
    public void clear() {
        remove();
    }

    @Override
    public String requestId() {
        return key;
    }

    private T remove() {
        return map.remove(key);
    }

}
