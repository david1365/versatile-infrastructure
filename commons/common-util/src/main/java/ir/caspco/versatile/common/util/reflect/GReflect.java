package ir.caspco.versatile.common.util.reflect;

import java.lang.reflect.Type;

import static ir.caspco.versatile.common.util.reflect.ReflectUtil.firstActualTypeArgument;
import static ir.caspco.versatile.common.util.reflect.ReflectUtil.getActualTypeArguments;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public interface GReflect extends BReflect {

    @SuppressWarnings("unchecked")
    default <T> Class<T> firstGenericClassObject() {
        return (Class<T>) firstActualTypeArgument(this.getClass());
    }

    default Type[] genericClassObjects() {
        return getActualTypeArguments(this.getClass());
    }

}
