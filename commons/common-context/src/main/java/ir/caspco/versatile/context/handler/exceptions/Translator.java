package ir.caspco.versatile.context.handler.exceptions;

import org.springframework.lang.Nullable;

import java.util.Locale;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public interface Translator {

    Locale PERSIAN = new Locale("fa", "IR");
    Locale ENGLISH = Locale.ENGLISH;


    String getMessage(String code, @Nullable Object[] args, Locale locale);


    default String getMessage(String code) {
        return getMessage(code, new Object[0], PERSIAN);
    }

    default String getMessage(String code, @Nullable Object[] args) {
        return getMessage(code, args, PERSIAN);
    }

    default String getMessage(String code, Locale locale) {
        return getMessage(code, new Object[0], locale);
    }

}
