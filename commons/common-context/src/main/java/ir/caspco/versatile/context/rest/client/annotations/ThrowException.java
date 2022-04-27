package ir.caspco.versatile.context.rest.client.annotations;

import ir.caspco.versatile.context.rest.client.exceptions.RestClientErrorException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ThrowException {
    Class<? extends RestClientErrorException> value() default RestClientErrorException.class;
}
