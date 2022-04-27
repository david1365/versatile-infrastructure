package ir.caspco.versatile.handler.exceptions;

import ir.caspco.versatile.context.handler.exceptions.cash.MessageHandlerCash;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;
import ir.caspco.versatile.context.handler.exceptions.services.ExceptionToDatabase;
import ir.caspco.versatile.correlation.RequestCorrelation;
import ir.caspco.versatile.handler.exceptions.cash.ExceptionMethodCash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


@Slf4j
@Order(-3)
@Component
//TODO from davood akbari: Do not forget to test.
public class WebFluxExceptionHandlerManagement extends AbstractErrorWebExceptionHandler {

    private final ExceptionMethodCash exceptionMethodCash;
    private final MessageHandlerCash messageHandlerCash;

    private final ExceptionToDatabase exceptionToDatabase;

    public WebFluxExceptionHandlerManagement(ErrorAttributes errorAttributes,
                                             WebProperties.Resources resources,
                                             ApplicationContext applicationContext,
                                             ServerCodecConfigurer serverCodecConfigurer,
                                             ExceptionMethodCash exceptionMethodCash,
                                             MessageHandlerCash messageHandlerCash,

                                             @Autowired(required = false)
                                                     ExceptionToDatabase exceptionToDatabase) {

        super(errorAttributes, resources, applicationContext);

        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());

        this.exceptionMethodCash = exceptionMethodCash;
        this.messageHandlerCash = messageHandlerCash;

        this.exceptionToDatabase = exceptionToDatabase;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @SuppressWarnings("unchecked")
    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

        RequestCorrelation.setId(request);

        Throwable exception = getError(request);
        log.error("Current Exception: ", exception);

        Throwable error = exception instanceof InvocationTargetException ?
                getTargetException((InvocationTargetException) exception) :
                exception;

        if (exceptionToDatabase != null) {
            exceptionToDatabase.logToDatabase(exception, error);
        }

        if (exceptionMethodCash.has(error.getClass())) {

            ErrorMessageHandler<?> errorMessageHandler = messageHandlerCash.getProvider(error);

            return (Mono<ServerResponse>) exceptionMethodCash.invoke(error.getClass(), error, errorMessageHandler);

        }

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }

    private Throwable getTargetException(InvocationTargetException exception) {
        Throwable realException = exception.getTargetException();

        while (realException instanceof InvocationTargetException) {
            realException = ((InvocationTargetException) realException).getTargetException();
        }

        log.error("Current Exception: ", realException);

        return realException;
    }
}
