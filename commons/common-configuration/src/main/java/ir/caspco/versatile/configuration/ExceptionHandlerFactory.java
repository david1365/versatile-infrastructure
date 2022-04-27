package ir.caspco.versatile.configuration;

import oracle.jdbc.OracleDatabaseException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class ExceptionHandlerFactory {

    private final Map<Class<? extends Throwable>, Function<Throwable, String>> classFunctionMap
            = new ConcurrentHashMap<>();


    public ExceptionHandlerFactory() {

        classFunctionMap.put(OracleDatabaseException.class, this::oracleDatabaseExceptionKey);

    }

    public String key(@Nullable Throwable throwable) {

        String defaultKey = "ir.caspco.versatile.gateway.main.configuration.dataBase.problem.message";

        if (throwable == null) {
            return defaultKey;
        }

        Throwable cause = throwable.getCause();
        defaultKey = cause == null ? defaultKey : cause.getClass().getName();

        Function<Throwable, String> keyFunction = classFunctionMap.get(throwable.getClass());
        return keyFunction != null ? keyFunction.apply(throwable) : defaultKey;

    }

    private String oracleDatabaseExceptionKey(Throwable oracleDatabaseException) {

        final String message = oracleDatabaseException.getMessage();

        int start = message.indexOf("ORA");
        int end = message.indexOf(":");

        return message.substring(start, end);

    }

}
