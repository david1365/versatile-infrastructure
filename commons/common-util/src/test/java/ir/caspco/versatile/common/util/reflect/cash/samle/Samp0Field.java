package ir.caspco.versatile.common.util.reflect.cash.samle;

import ir.caspco.versatile.common.util.reflect.cash.SampleCash;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@SampleClassAnnotation
public class Samp0Field {

    @SampleMethodAnnotation(Exception.class)
    public String m1() {
        return SampleCash.sampleString;
    }

    @SampleStringMethodAnnotation({"k4", "k5"})
    public String m2() {
        return "k4, k5";
    }

}
