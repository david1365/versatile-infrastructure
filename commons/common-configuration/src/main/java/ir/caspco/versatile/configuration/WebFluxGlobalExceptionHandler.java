package ir.caspco.versatile.configuration;


import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import ir.caspco.versatile.context.handler.exceptions.Translator;
import ir.caspco.versatile.context.handler.exceptions.annotations.WebFluxControllerAdvice;
import ir.caspco.versatile.context.handler.exceptions.annotations.WebFluxExceptionHandler;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorDescription;
import ir.caspco.versatile.context.handler.exceptions.message.ErrorMessageHandler;
import ir.caspco.versatile.context.jms.client.exceptions.CoreException;
import ir.caspco.versatile.context.jms.client.model.FaultDetails;
import ir.caspco.versatile.context.rest.client.common.esb.EsbErrorResult;
import ir.caspco.versatile.context.rest.client.common.esb.EsbException;
import ir.caspco.versatile.context.validation.exception.ValidationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@WebFluxControllerAdvice
public class WebFluxGlobalExceptionHandler {

    private final Translator translator;
    private final ExceptionHandlerFactory exceptionHandlerFactory;


    public WebFluxGlobalExceptionHandler(@Qualifier("defaultTranslator") Translator translator, ExceptionHandlerFactory exceptionHandlerFactory) {

        this.translator = translator;
        this.exceptionHandlerFactory = exceptionHandlerFactory;

    }


    @WebFluxExceptionHandler(ServerWebInputException.class)
    public Mono<ServerResponse> handleServerWebInputException(ServerWebInputException serverWebInputException,
                                                              ErrorMessageHandler<?> errorMessageHandler) {

        List<ErrorDescription> errorMessages = new ArrayList<>();
        if (serverWebInputException instanceof WebExchangeBindException) {

            WebExchangeBindException webExchangeBindException = ((WebExchangeBindException) serverWebInputException);

            Object target = webExchangeBindException.getBindingResult().getTarget();
            final String pojoName = target == null ? "" : target.getClass().getName();

            errorMessages = webExchangeBindException.getAllErrors()
                    .stream()
                    .map(objectError -> {

                        ConstraintViolation<?> constraintViolation = objectError.unwrap(ConstraintViolation.class);
                        String messageTemplate = constraintViolation.getMessageTemplate()
                                .replace("{", "")
                                .replace("}", "")
                                .replace("$", "");

                        String fieldName = objectError instanceof FieldError ?
                                pojoName + "." + ((FieldError) objectError).getField() : pojoName;

                        String fieldNameEn_US = translator.getMessage(fieldName, Translator.ENGLISH);
                        String fieldNameFa_IR = translator.getMessage(fieldName, Translator.PERSIAN);

                        String messageEn_US = translator.getMessage(messageTemplate, Translator.ENGLISH);
                        String messageFa_IR = translator.getMessage(messageTemplate, Translator.PERSIAN);

                        return ErrorDescription.builder()
                                .en_US(fieldNameEn_US + ": " + messageEn_US)
                                .fa_IR(fieldNameFa_IR + ": " + messageFa_IR)
                                .build();

                    })
                    .collect(Collectors.toList());

        } else {

            String key = serverWebInputException.getClass().getName();
            translate(errorMessages, key);

        }

        return ServerResponse.status(errorMessageHandler.status(serverWebInputException))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorMessageHandler.message(serverWebInputException, errorMessages)));
    }

    @WebFluxExceptionHandler(GatewayException.class)
    public Mono<ServerResponse> handleGatewayException(GatewayException gatewayException,
                                                       ErrorMessageHandler<?> errorMessageHandler) {

        return ServerResponse.status(errorMessageHandler.status(gatewayException))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        errorMessageHandler.message(gatewayException, gatewayException.errorDescriptions(translator))
                ));
    }

    @WebFluxExceptionHandler(DataIntegrityViolationException.class)
    public Mono<ServerResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException dataIntegrityViolationException,
            ErrorMessageHandler<?> errorMessageHandler
    ) {

        String key = exceptionHandlerFactory.key(dataIntegrityViolationException.getRootCause());

        final List<ErrorDescription> errorDescriptions = new ArrayList<>();
        translate(errorDescriptions, key);

        return ServerResponse.status(errorMessageHandler.status(dataIntegrityViolationException))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        errorMessageHandler.message(dataIntegrityViolationException, errorDescriptions)
                ));

    }

    @WebFluxExceptionHandler(CoreException.class)
    public Mono<ServerResponse> handleCoreException(CoreException coreException,
                                                    ErrorMessageHandler<?> errorMessageHandler) {

        return ServerResponse.status(errorMessageHandler.status(coreException))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        errorMessageHandler.message(coreException, CoreExceptionToErrorDescription(coreException))
                ));

    }

    @WebFluxExceptionHandler(EsbException.class)
    public Mono<ServerResponse> handleEsbException(EsbException esbException,
                                                   ErrorMessageHandler<?> errorMessageHandler) {

        HttpStatus status = errorMessageHandler.status(esbException);

        List<ErrorDescription> errorDescriptions = new ArrayList<>();

        Throwable target = esbException.getTarget();
        if (target instanceof HttpClientErrorException) {

            final String unknownMessage = "unknown";

            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) target;

            status = httpClientErrorException.getStatusCode();

            EsbErrorResult esbErrorResult = Shift.just(httpClientErrorException.getResponseBodyAsString())
                    .toShift(EsbErrorResult.class)
                    .toObject();

            String en_us = esbErrorResult.getDescription().getEn_US();
            String fa_ir = esbErrorResult.getDescription().getFa_IR();

            String exceptionDetail = esbErrorResult.getExceptionDetail();
            en_us = unknownMessage.equalsIgnoreCase(en_us) || en_us == null ? translator.getMessage(exceptionDetail, Translator.ENGLISH) : en_us;
            fa_ir = unknownMessage.equalsIgnoreCase(fa_ir) || fa_ir == null ? translator.getMessage(exceptionDetail, Translator.PERSIAN) : fa_ir;

            errorDescriptions.add(
                    ErrorDescription.builder()
                            .en_US(en_us)
                            .fa_IR(fa_ir)
                            .build());

        } else {

            final String key = target.getClass().getName();
            translate(errorDescriptions, key);

        }


        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        errorMessageHandler.message(esbException, errorDescriptions)
                ));

    }

    @WebFluxExceptionHandler(ValidationException.class)
    public Mono<ServerResponse> handleValidationException(ValidationException validationException,
                                                          ErrorMessageHandler<?> errorMessageHandler) {

        final String recordKey = "ir.caspco.versatile.gateway.ValidationException.record.number";
        final String defaultFormat = "%s%s: %s";

        List<ErrorDescription> errorDescriptions = new ArrayList<>();

        validationException.nodeViolations().forEach((index, nodeViolationsMap) -> {

            final String strRecordNumber_en_US = index == -1 ? "" : translator.getMessage(recordKey, Translator.ENGLISH) + " [" + index + "] ";
            final String strRecordNumber_fa_IR = index == -1 ? "" : translator.getMessage(recordKey, Translator.PERSIAN) + " [" + index + "] ";

            nodeViolationsMap.forEach((fieldName, messages) ->

                    messages.forEach(messageKey -> {

                        final String message_en_US = String.format(defaultFormat,
                                strRecordNumber_en_US,
                                translator.getMessage(fieldName, Translator.ENGLISH),
                                translator.getMessage(messageKey, Translator.ENGLISH)
                        );

                        final String message_fa_IR = String.format(defaultFormat, strRecordNumber_fa_IR,
                                translator.getMessage(fieldName, Translator.PERSIAN),
                                translator.getMessage(messageKey, Translator.PERSIAN)
                        );

                        errorDescriptions.add(
                                ErrorDescription.builder()
                                        .en_US(message_en_US)
                                        .fa_IR(message_fa_IR)
                                        .build());

                    })

            );

        });

        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        errorMessageHandler.message(validationException, errorDescriptions)
                ));

    }

    @WebFluxExceptionHandler(Exception.class)
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Mono<ServerResponse> handleException(Exception exception,
                                                ErrorMessageHandler<?> errorMessageHandler) {

        final List<ErrorDescription> errorDescriptions = new ArrayList<>();

        String key = exception.getClass().getName();

        if (exception instanceof javax.validation.ValidationException) {

            Throwable cause = exception.getCause();

            Class<?> thisClass = getClass();

            Method[] methods = thisClass.getMethods();

            for (Method method : methods) {

                if (method.isAnnotationPresent(WebFluxExceptionHandler.class)) {

                    WebFluxExceptionHandler webFluxExceptionHandler = method.getAnnotation(WebFluxExceptionHandler.class);

                    if (!webFluxExceptionHandler.value().isAssignableFrom(Exception.class) &&
                            webFluxExceptionHandler.value().isAssignableFrom(cause.getClass())) {

                        return (Mono<ServerResponse>) method.invoke(this, cause, errorMessageHandler);
                    }
                }
            }

            key = cause.getClass().getName();
        }

        translate(errorDescriptions, key);

        return ServerResponse.status(errorMessageHandler.status(exception))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorMessageHandler.message(exception, errorDescriptions)));

    }

    private List<ErrorDescription> CoreExceptionToErrorDescription(CoreException coreException) {

        List<ErrorDescription> errorDescriptions = new ArrayList<>();

        FaultDetails faultDetails = coreException.getFaultDetails();

        String key, key2;
        if (faultDetails == null) {

            key = key2 = CoreException.class.getName();
        } else if (faultDetails.getCode() == null) {

            key = key2 = faultDetails.getFaultCode();
        } else {

            key = faultDetails.getEn_US();
            key2 = faultDetails.getFa_IR();
        }

        translate(errorDescriptions, key, key2);

        return errorDescriptions;
    }

    private void translate(List<ErrorDescription> errorDescriptions, String key) {

        translate(errorDescriptions, key, key);
    }

    private void translate(List<ErrorDescription> errorDescriptions, String key, String key2) {

        errorDescriptions.add(
                ErrorDescription.builder()
                        .en_US(translator.getMessage(key, Translator.ENGLISH))
                        .fa_IR(translator.getMessage(key2, Translator.PERSIAN))
                        .build());
    }

}