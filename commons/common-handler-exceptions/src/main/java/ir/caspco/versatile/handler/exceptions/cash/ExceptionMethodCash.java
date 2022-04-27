package ir.caspco.versatile.handler.exceptions.cash;

import ir.caspco.versatile.common.util.reflect.cash.MethodCash;
import ir.caspco.versatile.common.util.reflect.cash.MethodInformation;
import ir.caspco.versatile.context.handler.exceptions.ReturnTypeException;
import ir.caspco.versatile.context.handler.exceptions.annotations.WebFluxControllerAdvice;
import ir.caspco.versatile.context.handler.exceptions.annotations.WebFluxExceptionHandler;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Component
public class ExceptionMethodCash extends MethodCash<Class<?>, WebFluxControllerAdvice, WebFluxExceptionHandler> {

    public ExceptionMethodCash(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    @SneakyThrows
    protected final void initPut(MethodInformation methodInformation,
                                 WebFluxControllerAdvice classAnnotation,
                                 WebFluxExceptionHandler methodAnnotation) {

        Method method = methodInformation.getMethod();

        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length >= 1 && !Throwable.class.isAssignableFrom(parameterTypes[0])) {
            throw new NoSuchMethodException("Parameter Types is not correct! Method name: " + method.getName());
        }

        if (!method.getReturnType().isAssignableFrom(Mono.class)) {
            throw new ReturnTypeException("ir.caspco.versatile.gateway.common.handler.exception.ReturnTypeException", method.getName());
        }

        put(methodAnnotation.value(), methodInformation);

    }

    //TODO from davood akbari: Find the best solution for 'hasMethod'.
    @Override
    public boolean has(Class<?> key) {
        return kvMap().keySet().stream()
                .anyMatch(type -> type.isAssignableFrom(key));
    }

    @Override
    public Optional<MethodInformation> get(Class<?> key) {
        return kvMap().keySet().stream()
                .filter(type -> type.isAssignableFrom(key))
                .sorted(this::getClassComparator)
                .map(type -> kvMap().get(type))
                .findFirst();
    }

    private int getClassComparator(Class<?> type1, Class<?> type2) {
        if (type2.isAssignableFrom(type1))
            return -1;
        else if (type1.isAssignableFrom(type2)) {
            return 1;
        } else {
            return 0;
        }
    }

}
