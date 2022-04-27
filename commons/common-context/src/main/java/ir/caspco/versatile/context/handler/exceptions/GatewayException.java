package ir.caspco.versatile.context.handler.exceptions;


import ir.caspco.versatile.context.handler.exceptions.message.ErrorDescription;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Getter
public class GatewayException extends RuntimeException {

    private Object[] args = new Object[0];
    private Integer resultNumber;


    public GatewayException() {
    }

    public GatewayException(int resultNumber) {
        this.resultNumber = resultNumber;
    }

    public GatewayException(int resultNumber, Object... args) {
        this.args = args;
        this.resultNumber = resultNumber;
    }

    public GatewayException(Object... args) {
        this.args = args;
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Object... args) {
        super(message);
        this.args = args;
    }

    public GatewayException(String message, Throwable cause, Object[] args) {
        super(message, cause);
        this.args = args;
    }

    public GatewayException(Throwable cause, Object... args) {
        super(cause);
        this.args = args;
    }

    public GatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.args = args;
    }

    public String[] getArgs(Translator translator, Locale locale) {

        String[] translatedArgs = new String[getArgs().length];

        for (int i = 0; i < getArgs().length; i++) {
            translatedArgs[i] = translator.getMessage(getArgs()[i].toString(), locale);
        }

        return translatedArgs;
    }

    public List<ErrorDescription> errorDescriptions(Translator translator) {
        List<ErrorDescription> errorDescriptions = new ArrayList<>();

        String key = getResultNumber() != null ?
                getResultNumber().toString() :
                (getMessage() != null ? getMessage() : getClass().getName());


        errorDescriptions.add(

                ErrorDescription.builder()
                        .en_US(translator.getMessage(key, getArgs(translator, Translator.ENGLISH), Translator.ENGLISH))
                        .fa_IR(translator.getMessage(key, getArgs(translator, Translator.PERSIAN), Translator.PERSIAN))
                        .build()

        );


        return errorDescriptions;

    }

}