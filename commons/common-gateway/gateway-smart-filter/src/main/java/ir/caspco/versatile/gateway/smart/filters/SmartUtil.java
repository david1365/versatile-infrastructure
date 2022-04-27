package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.gateway.smart.filters.annotations.AuxiliaryBody;
import ir.caspco.versatile.gateway.smart.filters.cash.NumberOfParametersException;
import ir.caspco.versatile.gateway.smart.filters.cash.DCash;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class SmartUtil {

    public static Object[] placement(Method method,
                                     ServerWebExchange exchange,
                                     DCash<?> dCash,
                                     DataBuffer body
    ) {

        return SmartUtil.placement(method, exchange, dCash, body, null);

    }

    //TODO from davdoo akbari: Find the best solution.
    public static Object[] placement(Method method,
                                     ServerWebExchange exchange,
                                     DCash<?> dCash,
                                     DataBuffer body,
                                     DataBuffer auxiliaryBody
    ) {
        final int parameterCount = 4;

        Class<?>[] types = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();

        int length = types.length;

        if (length > parameterCount || !isCountBodyCorrect(method)) {
            throw new NumberOfParametersException("The number of parameters is greater. Method name:'" + method.getName() + "'");
        }

        Object[] argsTmp = new Object[length];

        for (int i = 0; i < length; i++) {
            if (DCash.class.isAssignableFrom(types[i])) {
                argsTmp[i] = dCash;
            } else if (ServerWebExchange.class.isAssignableFrom(types[i])) {
                argsTmp[i] = exchange;
            } else if (parameters[i].isAnnotationPresent(AuxiliaryBody.class)) {
                argsTmp[i] = auxiliaryBody == null ? null : Shift.just(auxiliaryBody).toShift(types[i]).toObject();
            } else {
                argsTmp[i] = Shift.just(body).toShift(types[i]).toObject();
            }
        }

        return argsTmp;

    }

    //TODO from davdoo akbari: Find the best solution.
    public static boolean isCountBodyCorrect(Method method) {

        Class<?>[] types = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();

        return 1 >= IntStream.range(0, types.length)
                .filter(index -> !ServerWebExchange.class.isAssignableFrom(types[index]))
                .filter(index -> !DCash.class.isAssignableFrom(types[index]))
                .filter(index -> !parameters[index].isAnnotationPresent(AuxiliaryBody.class))
                .count();

    }

}
