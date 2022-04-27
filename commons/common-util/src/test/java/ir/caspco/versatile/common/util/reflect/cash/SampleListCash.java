package ir.caspco.versatile.common.util.reflect.cash;

import ir.caspco.versatile.common.util.reflect.cash.samle.SampleClassAnnotation;
import ir.caspco.versatile.common.util.reflect.cash.samle.SampleStringMethodAnnotation;
import org.springframework.context.ApplicationContext;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class SampleListCash extends MethodListCash<String, SampleClassAnnotation, SampleStringMethodAnnotation> {

    public static final String sampleString = "Nazanin";


    public SampleListCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public boolean filter(Object object) {
        return object.getClass().getDeclaredFields().length >= 1;
    }

    @Override
    protected void initPut(MethodInformation methodInformation, SampleClassAnnotation classAnnotation, SampleStringMethodAnnotation methodAnnotation) {

        for (String value : methodAnnotation.value()) {
            put(value, methodInformation);
        }

    }

}
