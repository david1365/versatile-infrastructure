package ir.caspco.versatile.context.handler.exceptions.annotations;

import java.lang.annotation.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebFluxExceptionHandler {
    Class<? extends Throwable> value();
}
