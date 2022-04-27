package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.cash.samle.SampleClassAnnotation;
import ir.caspco.versatile.common.util.reflect.cash.samle.SampleMethodAnnotation;
import org.springframework.context.ApplicationContext;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class SampleCash extends MethodCash<Class<?>, SampleClassAnnotation, SampleMethodAnnotation> {

    public static final String sampleString = "Nazanin";


    public SampleCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public boolean filter(Object object) {
        return object.getClass().getDeclaredFields().length >= 1;
    }

    @Override
    protected void initPut(MethodInformation methodInformation, SampleClassAnnotation classAnnotation, SampleMethodAnnotation methodAnnotation) {
        put(methodAnnotation.value(), methodInformation);
    }

}
