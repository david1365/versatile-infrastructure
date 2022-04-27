package ir.caspco.versatile.common.util.reflect;

import lombok.SneakyThrows;

import java.lang.annotation.Annotation;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
public interface BReflect {

    @SneakyThrows
    default Class<?> realClassObject() {
        return ReflectUtil.realClassObject(this.getClass());
    }

    default String realName() {
        return ReflectUtil.getRealName(this.getClass());
    }

    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getClass().isAnnotationPresent(annotationClass);
    }

    default <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return getClass().getAnnotation(annotationClass);
    }

}
