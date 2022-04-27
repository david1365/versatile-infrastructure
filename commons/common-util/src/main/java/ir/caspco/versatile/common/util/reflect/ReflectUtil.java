package ir.caspco.versatile.common.util.reflect;

import ir.caspco.versatile.common.util.exceptions.IllegalAccessExceptionOrInvocationTargetException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Slf4j
public class ReflectUtil {

    public static List<PropertyDescriptor> getPropertyDescriptors(Object anObject) {
        return getPropertyDescriptors(anObject.getClass());
    }

    @SneakyThrows
    public static List<PropertyDescriptor> getPropertyDescriptors(Class<?> aClass) {

        BeanInfo beanInfo = Introspector.getBeanInfo(aClass);

        return Arrays.stream(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor -> !"class".equals(propertyDescriptor.getName()))
                .collect(Collectors.toList());

    }

    public static Type firstActualTypeArgument(Class<?> aClass) {
        return getActualTypeArguments(aClass)[0];
    }

    public static Type[] getActualTypeArguments(Type t) {

        if (t instanceof ParameterizedType) {
            return ((ParameterizedType) t).getActualTypeArguments();
        } else {
            Class<?> aClass = (Class<?>) t;

            Type superClass = aClass.getGenericSuperclass();

            if (superClass == null || Object.class.getName().equals(superClass.getTypeName())) {
                return Arrays.stream(aClass.getGenericInterfaces())
                        .map(ReflectUtil::getActualTypeArguments)
                        .reduce(ArrayUtils::addAll)
                        .orElse(new Type[0]);
            }

            return getActualTypeArguments(superClass);
        }

    }

    public static <T> Method getMethod(T target, String name, Class<?>... parameterTypes) {
        return getMethod(target.getClass(), name, parameterTypes);
    }

    @SneakyThrows
    public static Method getMethod(Class<?> aClass, String name, Class<?>... parameterTypes) {
        return aClass.getMethod(name, parameterTypes);
    }

    @SneakyThrows
    public static Object invoke(Method method, Object object, Object... args) {
        return method.invoke(object, args);
    }

    @SneakyThrows
    public static Object smartInvoke(Method method, Object object, Object... args) {

        Class<?>[] types = method.getParameterTypes();
        Object[] smartArgs = new Object[types.length];

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < args.length; j++) {

                if (types[i].isAssignableFrom(args[j].getClass())) {

                    smartArgs[i] = args[j];

                    args = ArrayUtils.remove(args, j);

                    break;

                }

            }
        }

        return method.invoke(object, smartArgs);

    }


    //TODO from davood akbari: Do not forget to test.
    public static String getRealName(Class<?> aClass) {

        String name = aClass.getName();
        int endIndex = name.indexOf("$$");
        return endIndex == -1 ? name : name.substring(0, endIndex);

    }

    @SneakyThrows
    //TODO from davood akbari: Do not forget to test.
    public static Class<?> realClassObject(Class<?> aClass) {

        if (!aClass.getName().contains("$$")) {
            return aClass;
        }

        String name = getRealName(aClass);

        return Class.forName(name);

    }

    public static Optional<Object> value(String fieldName, Object target) {

        return getPropertyDescriptors(target).stream()
                .filter(propertyDescriptor -> fieldName.equalsIgnoreCase(propertyDescriptor.getName()))
                .map(propertyDescriptor -> {
                    try {

                        return Optional.ofNullable(propertyDescriptor.getReadMethod().invoke(target));

                    } catch (IllegalAccessException | InvocationTargetException e) {

                        e.printStackTrace();
                        log.error("IllegalAccessException | InvocationTargetException", e);

                        throw new IllegalAccessExceptionOrInvocationTargetException();

                    }
                })
                .findFirst()
                .filter(Optional::isPresent)
                .orElse(Optional.empty());

    }

    //TODO from davood akbari: Do not forget to test.
    public static List<String> fieldNames(Object object, Class<? extends Annotation> annotationClass) {

        if (object != null) {
            return getPropertyDescriptors(object).stream()
                    .map(propertyDescriptor -> {

                        if (isAnnotationPresent(object.getClass(), propertyDescriptor, annotationClass)) {
                            return Optional.of(propertyDescriptor.getName());
                        }

                        return Optional.empty();
                    })
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();

    }

    public static List<String> fieldNames(List<?> objects, Class<? extends Annotation> annotationClass) {

        if (objects != null && !objects.isEmpty()) {
            return fieldNames(objects.get(0), annotationClass);
        }

        return Collections.emptyList();

    }

    public static List<?> fieldValues(List<?> objects, Class<? extends Annotation> annotationClass) {

        return fieldNames(objects, annotationClass).stream()
                .flatMap(fieldName -> objects.stream().map(object -> value(fieldName, object)))
                .filter(Optional::isPresent)
                .distinct()
                .map(Optional::get)
                .collect(Collectors.toList());

    }

    public static List<Optional<?>> fieldValues(Object object, Class<? extends Annotation> annotationClass) {

        return fieldNames(object, annotationClass).stream()
                .map(fieldName -> value(fieldName, object))
                .collect(Collectors.toList());

    }

    //TODO from davood akbari: Do not forget to test.
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> fieldValue(Object object, Class<? extends Annotation> annotationClass) {

        return (Optional<T>) fieldNames(object, annotationClass).stream()
                .map(fieldName -> value(fieldName, object))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

    }

    @SneakyThrows
    public static boolean isAnnotationPresent(Class<?> classObject, PropertyDescriptor propertyDescriptor, Class<? extends Annotation> annotationClass) {

        boolean isProperty = propertyDescriptor.getReadMethod().isAnnotationPresent(annotationClass) ||
                propertyDescriptor.getWriteMethod().isAnnotationPresent(annotationClass);


        List<Field> fileds = declaredFields(classObject);

        boolean isField = fileds.stream()
                .filter(field -> field.getName().equals(propertyDescriptor.getName()))
                .anyMatch(field -> field.isAnnotationPresent(annotationClass));

        return isProperty || isField;

    }

    public static List<Field> declaredFields(Class<?> classObject) {

        Class<?> superClass = classObject.getSuperclass();

        List<Field> superClassFields = new ArrayList<>();
        if (superClass != null) {
            superClassFields = declaredFields(superClass);
        }

        superClassFields.addAll(Arrays.asList(classObject.getDeclaredFields()));
        return superClassFields;

    }

    public static Type[] fieldGenericClassObject(Field field) {

        return field.getGenericType() instanceof ParameterizedType ?
                ((ParameterizedType) field.getGenericType()).getActualTypeArguments() :
                new Type[0];

    }

}
