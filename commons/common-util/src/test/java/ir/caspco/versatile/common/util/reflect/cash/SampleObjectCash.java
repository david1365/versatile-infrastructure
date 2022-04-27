package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.cash.samle.SampleClassAnnotation;
import org.springframework.context.ApplicationContext;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class SampleObjectCash extends ObjectCash<String, Object, SampleClassAnnotation> {

    @Override
    protected void initPut(Object object, SampleClassAnnotation classAnnotation) {
        kvMap().put(classAnnotation.value(), object);
    }

    public SampleObjectCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public boolean filter(Object object) {
        return object.getClass().getDeclaredFields().length >= 1;
    }

}
