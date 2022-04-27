package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.context.handler.exceptions.Translator;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Component("showDefaultTranslator")
public class ShowDefaultTranslator implements Translator {

    protected final MessageSource messageSource;


    public ShowDefaultTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public String getMessage(String code, @Nullable Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return "hhhhhhhh";//messageSource.getMessage(code, args, locale);
        }
    }

}
