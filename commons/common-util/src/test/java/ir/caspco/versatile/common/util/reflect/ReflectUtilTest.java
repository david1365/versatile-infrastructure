package ir.caspco.versatile.common.util.reflect;

import ir.caspco.versatile.common.util.OneNotNull;
import ir.caspco.versatile.common.util.model.SampleModel;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

class CFace implements BFace {
    public Integer integer;
    public List<Integer> integers;
    public Map<Boolean, Byte> booleanByteMap;

}

interface BFace extends Face<Integer, Boolean> {
}

interface Face<E, B> {
    @SuppressWarnings("unchecked")
    default Class<E> firstGenericClassObject() {
        return (Class<E>) ReflectUtil.firstActualTypeArgument(this.getClass());
    }
}

class CGen extends BGen<Integer, Boolean> {

}

class BGen<E, B> extends Gen<E, B> {
    E entity;
    B bool;
}

class Gen<E, B> {
    E entity;
    B bool;
}

public class ReflectUtilTest {

    private String expectedString = "davood";
    private int expectedInteger = 123;

    @Test
    public void genericClassObjects() {
        Type[] expectedTypeArguments = new Type[2];
        expectedTypeArguments[0] = Integer.class;
        expectedTypeArguments[1] = Boolean.class;

        Gen<Integer, Boolean> integerGen = new CGen();

        Type[] actualTypeArguments = ReflectUtil.getActualTypeArguments(integerGen.getClass());

        assertArrayEquals(expectedTypeArguments, actualTypeArguments);
    }

    @Test
    public void firstGenericClassObject() {
        Gen<Integer, Boolean> integerGen = new CGen();

        Type integerClass = ReflectUtil.firstActualTypeArgument(integerGen.getClass());

//----------------------------------------------------------------------------------------------------------------------

        assertEquals(Integer.class, integerClass);

        BFace bFace = new CFace();
        integerClass = ReflectUtil.firstActualTypeArgument(bFace.getClass());

        assertEquals(Integer.class, integerClass);
//----------------------------------------------------------------------------------------------------------------------

        integerClass = bFace.firstGenericClassObject();

        assertEquals(Integer.class, integerClass);

    }

    @Test
    public void fieldGenericClassObject() {
        List<Type> expectedIntegerClassList = new ArrayList<>();
        expectedIntegerClassList.add(Integer.class);
        expectedIntegerClassList.add(Boolean.class);
        expectedIntegerClassList.add(Byte.class);

        List<Type> actualIntegerClassList = Arrays.asList(Arrays.stream(CFace.class.getFields())
                .map(ReflectUtil::fieldGenericClassObject)
                .reduce(ArrayUtils::addAll)
                .orElse(new Type[0]));

        assertEquals(expectedIntegerClassList, actualIntegerClassList);
    }

    @Test
    void getFieldValues() {

        SampleModel sampleModel = SampleModel.builder()
                .string("davood")
                .integer(20)
                .longNumber(10L)
                .build();

        List<Optional<?>> objects = ReflectUtil.fieldValues(sampleModel, OneNotNull.class);

        objects.get(0).ifPresent(object -> assertTrue(object instanceof Integer));

        objects.get(1).ifPresent(object -> assertTrue(object instanceof Long));

        objects.get(2).ifPresent(object -> assertTrue(object instanceof String));

    }

    @Test
    void getFieldValue() {

        SampleModel sampleModel = SampleModel.builder()
                .string("davood")
                .integer(20)
                .longNumber(10L)
                .build();

        Optional<?> value = ReflectUtil.fieldValue(sampleModel, OneNotNull.class);

        value.ifPresent(object -> assertTrue(object instanceof Integer));

    }

    @Test
    @SneakyThrows
    void smartInvoke() {

        Method method1 = this.getClass().getMethod("smartMethod1", Integer.class, String.class);

        ReflectUtil.smartInvoke(method1, this, expectedInteger);

        Method method2 = this.getClass().getMethod("smartMethod2", Integer.class, String.class);

        ReflectUtil.smartInvoke(method2, this, expectedString);

        method1 = this.getClass().getMethod("smartMethod1", String.class);

        ReflectUtil.smartInvoke(method1, this, expectedString, expectedInteger);

        ReflectUtil.smartInvoke(method1, this, expectedInteger, expectedString);

    }

    public void smartMethod1(Integer integer, String str) {

        assertEquals(expectedInteger, integer);

        assertNull(str);

    }

    public void smartMethod2(Integer integer, String str) {

        assertEquals(expectedString, str);

        assertNull(integer);

    }

    public void smartMethod1(String str) {

        assertEquals(expectedString, str);

    }

    static class SuperClass {
        private String f1;
        private String f2;
    }

    static class Parent extends SuperClass {
        private String f3;
        private String f4;
    }

    static class Child extends Parent {
        private String f5;
        private String f6;
    }

    @Test
    void declaredFields() {

        List<Field> expectedFields = new ArrayList<>();
        expectedFields.addAll(Arrays.asList(SuperClass.class.getDeclaredFields()));
        expectedFields.addAll(Arrays.asList(Parent.class.getDeclaredFields()));
        expectedFields.addAll(Arrays.asList(Child.class.getDeclaredFields()));


        Child child = new Child();

        List<Field> actualFields = ReflectUtil.declaredFields(child.getClass());

        assertEquals(expectedFields, actualFields);

    }
}