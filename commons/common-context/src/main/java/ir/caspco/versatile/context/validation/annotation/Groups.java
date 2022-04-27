package ir.caspco.versatile.context.validation.annotation;

import java.lang.annotation.*;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Groups {
    Class<?>[] value() default {};
}
