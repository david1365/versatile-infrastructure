package ir.caspco.versatile.common.util.reflect.cash.samle;

import ir.caspco.versatile.common.util.reflect.cash.SampleCash;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@SampleClassAnnotation("ir.hampa")
public class SampObject1Field {

    private String a;


    @SampleMethodAnnotation(NullPointerException.class)
    public String m1() {
        return SampleCash.sampleString;
    }


    @SampleStringMethodAnnotation({"k1", "k2"})
    public String m2() {
        return "k1, k2";
    }

    @SampleStringMethodAnnotation({"k2", "k3"})
    public String m3() {
        return "k2, k3";
    }
}
