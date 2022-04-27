package ir.caspco.versatile.context.rest.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ResponsePath {
    String serviceName() default "";

    String servicePath() default "";

    Header[] headers() default {};

    Class<?> outputType() default Void.class;
}
