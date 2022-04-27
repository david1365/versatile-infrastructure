package ir.caspco.versatile.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.common.util.reflect.GReflect;
import ir.caspco.versatile.context.common.util.result.Result;
import ir.caspco.versatile.context.rest.client.annotations.*;
import ir.caspco.versatile.context.rest.client.exceptions.ClassObjectNotFoundRuntimeException;
import ir.caspco.versatile.context.rest.client.exceptions.ResponsePathNotFoundRuntimeException;
import ir.caspco.versatile.context.rest.client.exceptions.RestClientErrorException;
import ir.caspco.versatile.context.rest.client.exceptions.ServerAddressNotFoundRuntimeException;
import ir.caspco.versatile.context.rest.client.interfaces.Get;
import ir.caspco.versatile.context.rest.client.interfaces.Post;
import ir.caspco.versatile.context.rest.client.model.ClientHeader;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public abstract class RestClient implements GReflect {

    private static final String headerValue = "application/json";
    private static String defaultDateFormat = JsonDateFormat.defaultDateFormat;

    private String dateFormat;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    public static void setDefaultDateFormat(String defaultDateFormat) {
        RestClient.defaultDateFormat = defaultDateFormat;
    }


    @PostConstruct
    @SneakyThrows
    public void initComponent() {

        Optional<ApplicationBaseUrl> optionalApplicationBaseUrl =
                Optional.ofNullable(this.getClass().getAnnotation(ApplicationBaseUrl.class));

        String applicationNameBaseUrlKey =
                optionalApplicationBaseUrl.map(ApplicationBaseUrl::value).orElseThrow(ServerAddressNotFoundRuntimeException::new);


        String applicationNameBaseUrl = getProperty(applicationNameBaseUrlKey);

        Optional<Headers> optionalHeaders = Optional.ofNullable(this.getClass().getAnnotation(Headers.class));
        List<ClientHeader> headers = optionalHeaders.map(headersIn -> getClientHeaders(headersIn.value())).orElse(Collections.emptyList());

        RestClientErrorException restClientErrorException = isAnnotationPresent(ThrowException.class) ?
                getAnnotation(ThrowException.class).value().newInstance() : null;

        config();

        for (Field field : this.getClass().getFields()) {

            Optional<ResponsePath> optionalResponsePath = Optional.ofNullable(field.getAnnotation(ResponsePath.class));
            String serviceNamePath = getProperty(optionalResponsePath.map(responsePath -> responsePath.serviceName().isEmpty() ? null : responsePath.serviceName()).orElse(
                    optionalResponsePath.map(ResponsePath::servicePath).orElseThrow(ResponsePathNotFoundRuntimeException::new)
            ));

            Class<?> output = optionalResponsePath.map(ResponsePath::outputType).orElseThrow(ClassObjectNotFoundRuntimeException::new);

            List<ClientHeader> fieldHeaders = optionalResponsePath.map(responsePathIn -> new ArrayList<>(getClientHeaders(responsePathIn.headers()))).orElse(new ArrayList<>());
            fieldHeaders.addAll(headers);

            if (Post.class.getSimpleName().equals(field.getType().getSimpleName())) {

                field.set(this, (Post) (value, request, uriVariables) -> post(
                        applicationNameBaseUrl,
                        serviceNamePath,
                        fieldHeaders,
                        output,
                        value,
                        request,
                        restClientErrorException,
                        uriVariables)
                );

            } else if (Get.class.getSimpleName().equals(field.getType().getSimpleName())) {

                field.set(this, (Get) (request, uriVariables) -> get(
                        applicationNameBaseUrl,
                        serviceNamePath,
                        fieldHeaders,
                        output,
                        request,
                        restClientErrorException,
                        uriVariables)
                );

            }

        }

    }

    private List<ClientHeader> getClientHeaders(Header[] headers) {
        return Arrays.stream(headers)
                .map(header -> ClientHeader.builder()
                        .key(header.key())
                        .value(getProperty(header.value()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    private void config() {

        dateFormat = isAnnotationPresent(JsonDateFormat.class) ?
                getAnnotation(JsonDateFormat.class).value() :
                defaultDateFormat;


        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        objectMapper.setDateFormat(format);

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setPrettyPrint(false);
        messageConverter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
        restTemplate.getMessageConverters().add(messageConverter);

    }

    private String getProperty(String key) {
        Optional<String> optionalApplicationName = Optional.ofNullable(environment.getProperty(key));

        return optionalApplicationName.orElse(key);
    }

    private String getPath(String applicationNameBaseUrlKey, String serviceNamePath) {
        return applicationNameBaseUrlKey + serviceNamePath;
    }

    private <R> Result<R> get(String applicationNameBaseUrlKey,
                              String serviceNamePath,
                              List<ClientHeader> headers,
                              Class<R> tClass,
                              @Nullable HttpEntity<?> request,
                              RestClientErrorException restClientErrorException,
                              Object... uriVariables) {

        String path = getPath(applicationNameBaseUrlKey, serviceNamePath);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO from davood akbari: Don't forget the test(headers)
        for (ClientHeader header : headers) {
            httpHeaders.set(header.getKey(), header.getValue());
        }

        if (request == null) {
            request = new HttpEntity<R>(httpHeaders);

        } else {
            httpHeaders.addAll(request.getHeaders());
            request = new HttpEntity<>(httpHeaders);
        }


        HttpEntity<?> finalRequest = request;
        return Result.accept(() -> {
            ResponseEntity<R> response = restTemplate.exchange(path, HttpMethod.GET, finalRequest, tClass, uriVariables);
            //TODO from davood akbari: Don't forget the test(uriVariables)
            return response.getBody();
        })
                .resultErrorException(restClientErrorException);
    }

    private Object convertToJson(HttpHeaders httpHeaders, Object value) {
        List<String> headers = httpHeaders.get(HttpHeaders.CONTENT_TYPE);
        boolean convertToJson = headers != null && headers.stream().anyMatch(headerValue::equalsIgnoreCase);

        return convertToJson ? Shift.just(value).dateFormat(dateFormat).toJson() : value;
    }

    private <I, R> Result<R> post(String applicationNameBaseUrlKey, String serviceNamePath, List<ClientHeader> headers, Class<R> rClass,
                                  @Nullable I value, @Nullable HttpEntity<?> request,
                                  RestClientErrorException restClientErrorException, Object... uriVariables) {

        String path = getPath(applicationNameBaseUrlKey, serviceNamePath);

        HttpHeaders httpHeaders = new HttpHeaders();

        if (request == null && headers.size() <= 0) {
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, headerValue);
        }
        //TODO from davood akbari: Don't forget the test(headers)
        for (ClientHeader header : headers) {
            httpHeaders.set(header.getKey(), header.getValue());
        }

        if (request == null) {
            request = new HttpEntity<>(convertToJson(httpHeaders, value), httpHeaders);

        } else {
            httpHeaders.addAll(request.getHeaders());
            Object body = value == null ? request.getBody() : value;

            request = new HttpEntity<>(convertToJson(httpHeaders, body), httpHeaders);
        }

        //TODO from davood akbari: Don't forget the test(uriVariables)
        HttpEntity<?> finalRequest = request;
        return Result.accept(() -> restTemplate.exchange(path, HttpMethod.POST, finalRequest, rClass, uriVariables).getBody())
                .resultErrorException(restClientErrorException);
    }

}
