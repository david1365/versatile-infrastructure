package ir.caspco.versatile.handler.exceptions.message.factory;

import ir.caspco.versatile.context.handler.exceptions.CheckModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@Qualifier("defaultCheckModule")
public class DefaultCheckModule implements CheckModule {

    @Override
    public Boolean check(Throwable error, String key) {

        return Arrays.stream(error.getStackTrace())
                .anyMatch(stackTraceElement -> stackTraceElement.getClassName().contains(key));

    }

}
