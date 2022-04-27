package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.common.util.EnvironmentUtil;
import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.common.util.reflect.BReflect;
import ir.caspco.versatile.context.validation.DValidator;
import ir.caspco.versatile.gateway.smart.filters.annotations.Filter;
import ir.caspco.versatile.gateway.smart.filters.annotations.Path;
import ir.caspco.versatile.gateway.smart.filters.cash.CashBuilder;
import ir.caspco.versatile.gateway.smart.filters.cash.DCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ir.caspco.versatile.common.util.reflect.ReflectUtil.invoke;
import static ir.caspco.versatile.gateway.smart.filters.annotations.Filter.ALL;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test for -> exchangeBodyRequest, exchangeBodyResponse, exchangeRequest, exchangeResponse
// with change place of  body and exchange.
public abstract class SmartGatewayFilter extends SimpleGatewayFilter implements BReflect {

    @Autowired
    protected EnvironmentUtil environmentUtil;

    @Autowired
    protected MethodInfo methodInfo;

    @Autowired
    protected DValidator dValidator;

    @Autowired
    protected CashBuilder<?> cashBuilder;

    private MethodInfoModel methodInfoModel;

    private String basePrefix = "";


    protected String getProperty(String key) {

        return prefix() + environmentUtil.getProperty(key);

    }

    protected String prefix() {

        if (isAnnotationPresent(Filter.class)) {

            String prefix = environmentUtil.getProperty(getAnnotation(Filter.class).prefix());

            return prefix.isEmpty() ? basePrefix : prefix;

        }

        return basePrefix;

    }

    protected String path() {

        if (isAnnotationPresent(Path.class)) {

            return environmentUtil.getProperty(getAnnotation(Path.class).value());

        }

        return "";

    }

    protected int order() {

        if (isAnnotationPresent(Filter.class)) {

            return getAnnotation(Filter.class).order();

        }

        return 0;

    }

    protected SmartGatewayFilter that() {
        return this;
    }

    public void init(String basePrefix) {

        this.basePrefix = environmentUtil.getProperty(basePrefix);

        methodInfoModel = methodInfo.Collect(prefix() + path(), getClass().getMethods());

    }

    public GatewayFilter apply() {

        return this.apply(order());

    }

    @Override
    protected ServerHttpRequest exchangeRequest(ServerWebExchange exchange, DataBuffer requestBody) {

        return mutateHttp(methodInfoModel.getRequest(), exchange, cashBuilder.build(exchange), requestBody).getRequest();

    }

    @Override
    public DataBuffer exchangeRequestBody(ServerWebExchange exchange, DataBuffer requestBody) {

        return bodyMutation(exchange, cashBuilder.build(exchange))
                .methodMap(methodInfoModel.getRequestBody())
                .body(requestBody)
                .mutation();

    }

    @Override
    protected ServerHttpResponse exchangeResponse(ServerWebExchange exchange, DataBuffer requestBody) {

        return mutateHttp(methodInfoModel.getResponse(), exchange, cashBuilder.build(exchange), requestBody).getResponse();

    }

    @Override
    protected DataBuffer exchangeResponseBody(ServerWebExchange exchange, DataBuffer requestBody, DataBuffer responseBody) {

        DCash<?> cash = cashBuilder.build(exchange);

        return bodyMutation(exchange, cash)
                .methodMap(methodInfoModel.getResponseBody())
                .body(responseBody)
                .auxiliaryBody(requestBody)
                .mutation();

    }

    private ServerWebExchange mutateHttp(Map<String, Method> methodMap,
                                         ServerWebExchange exchange,
                                         DCash<?> dCash,
                                         DataBuffer body) {
        if (methodMap == null || methodMap.isEmpty()) {
            return exchange;
        }

        ServerWebExchange mutateExchange = exchange;

        Method mutatedMethod = methodMap.get(getKey(exchange));
        if (mutatedMethod != null) {

            mutateExchange = mutateHttp(exchange, dCash, mutatedMethod, body);

        }

        Method forAllMutatedMethods = methodMap.get(ALL);
        if (forAllMutatedMethods != null) {

            return mutateHttp(mutateExchange, dCash, forAllMutatedMethods, body);

        }

        return mutateExchange;
    }

    private ServerWebExchange mutateHttp(ServerWebExchange exchange, DCash<?> dCash, Method method, DataBuffer body) {

        Object[] args = SmartUtil.placement(method, exchange, dCash, body);

        dValidator.validateParameters(this, method, args);

        return (ServerWebExchange) invoke(method, this, args);

    }

    private String getKey(ServerWebExchange exchange) {
        return exchange.getRequest().getPath().toString();
    }

    private BodyMutation bodyMutation(ServerWebExchange exchange, DCash<?> cash) {
        return new BodyMutation(exchange, cash);
    }


    private class BodyMutation {
        private final ServerWebExchange exchange;

        private DataBuffer body;
        private DataBuffer auxiliaryBody;
        private final DCash<?> cash;

        private Map<String, List<Method>> methodMap = methodInfoModel.getRequestBody();


        public BodyMutation(ServerWebExchange exchange, DCash<?> cash) {

            this.exchange = exchange;
            this.cash = cash;

        }

        public BodyMutation body(DataBuffer body) {
            this.body = Shift.cloneDataBuffer(body);

            return this;
        }

        public BodyMutation auxiliaryBody(DataBuffer auxiliaryBody) {
            this.auxiliaryBody = Shift.cloneDataBuffer(auxiliaryBody);

            return this;
        }

        public BodyMutation methodMap(Map<String, List<Method>> methodMap) {
            this.methodMap = methodMap;

            return this;
        }

        private DataBuffer mutation() {

            MediaType contentType = exchange.getRequest().getHeaders().getContentType();

            if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)) {

                return mutateBody();
            }

            return body;
        }

        private DataBuffer mutateBody() {

            if (methodMap == null || methodMap.isEmpty()) {
                return body;
            }

            Map<String, Object> mutatedBody = new HashMap<>();

            List<Method> mutatedMethods = methodMap.get(getKey(exchange));
            if (mutatedMethods != null) {

                mutateBody(mutatedMethods, mutatedBody);

            }

            List<Method> forAllMutatedMethods = methodMap.get(ALL);
            if (forAllMutatedMethods != null) {

                mutateBody(forAllMutatedMethods, mutatedBody);

            }

            return mutatedBody.isEmpty() ? body : Shift.just(mutatedBody).toDataBuffer();
        }

        @SuppressWarnings("unchecked")
        private void mutateBody(List<Method> mutatedMethods,
                                Map<String, Object> mutatedBody) {

            mutatedMethods.forEach(method -> {

                Object[] args = SmartUtil.placement(method, exchange, cash, body, auxiliaryBody);

                dValidator.validateParameters(that(), method, args);

                Object result = invoke(method, that(), args);

                mutatedBody.putAll(Shift.just(result).toShift(Map.class).toObject());

            });

        }

    }

}

