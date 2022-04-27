package ir.caspco.versatile.context.handler.exceptions.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WebFluxControllerAdvice {
}
