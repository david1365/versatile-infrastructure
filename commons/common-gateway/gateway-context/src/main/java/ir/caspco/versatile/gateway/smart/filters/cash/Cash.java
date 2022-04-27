package ir.caspco.versatile.gateway.smart.filters.cash;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface Cash<T> {

    void put(T target);

    T get();

    void clear();

    String requestId();

}
