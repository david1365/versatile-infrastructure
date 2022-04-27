package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.common.util.BufferUtil;
import ir.caspco.versatile.common.util.Shift;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test for -> exchangeRequestBody, exchangeRequest, exchangeResponseBody, exchangeResponse
@Transactional
public abstract class SimpleGatewayFilter {

    private static final Map<HttpMethod, Boolean> methodHasBody = new ConcurrentHashMap<>();

    protected ServerHttpRequest exchangeRequest(ServerWebExchange exchange, DataBuffer requestBody) {
        return exchange.getRequest();
    }

    protected DataBuffer exchangeRequestBody(ServerWebExchange exchange, DataBuffer requestBody) {
        return requestBody;
    }

    protected ServerHttpResponse exchangeResponse(ServerWebExchange exchange, DataBuffer requestBody) {
        return exchange.getResponse();
    }

    protected DataBuffer exchangeResponseBody(ServerWebExchange exchange, DataBuffer requestBody, DataBuffer responseBody) {
        return responseBody;
    }

    @PostConstruct
    private void init() {

        methodHasBody.put(HttpMethod.POST, true);
        methodHasBody.put(HttpMethod.PUT, true);
        methodHasBody.put(HttpMethod.PATCH, true);

    }

    public GatewayFilter apply(int order) {

        return new OrderedGatewayFilter(
                (exchange, chain) -> {

                    ServerHttpRequest originalRequest = exchange.getRequest();

                    if (methodHasBody.get(originalRequest.getMethod()) != null) {

                        return DataBufferUtils.join(exchange.getRequest().getBody())
                                .flatMap(requestBody -> getChain(exchange, chain, requestBody));

                    }

                    return getChain(exchange, chain);

                },

                order);

    }

    private Mono<Void> getChain(ServerWebExchange exchange, GatewayFilterChain chain) {
        return getChain(exchange, chain, null);
    }

    private Mono<Void> getChain(ServerWebExchange exchange, GatewayFilterChain chain, DataBuffer requestBody) {

        ServerHttpRequest mutatedHttpRequest = getServerHttpRequest(exchange, Shift.cloneDataBuffer(requestBody));

        ServerHttpResponse mutatedHttpResponse = getServerHttpResponse(exchange, Shift.cloneDataBuffer(requestBody));

        final ServerWebExchange mutatedExchange = exchange.mutate()
                .request(mutatedHttpRequest)
                .response(mutatedHttpResponse)
                .build();


        return chain.filter(
                mutatedExchange.mutate()
                        .request(exchangeRequest(mutatedExchange, Shift.cloneDataBuffer(requestBody)))
                        .response(exchangeResponse(mutatedExchange, Shift.cloneDataBuffer(requestBody)))
                        .build()
        );
    }

    private ServerHttpRequest getServerHttpRequest(ServerWebExchange exchange, DataBuffer requestBody) {

        final ServerWebExchange originalExchange = exchange;
        final ServerHttpRequest originalRequest = originalExchange.getRequest();

        return new ServerHttpRequestDecorator(originalRequest) {

            @Override
            public HttpHeaders getHeaders() {

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(originalRequest.getHeaders());

                httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);

                return httpHeaders;

            }

            @Override
            public Flux<DataBuffer> getBody() {

                if (requestBody == null) {
                    return super.getBody();
                }

                return Flux.just(exchangeRequestBody(originalExchange, requestBody));

            }

        };
    }

    private ServerHttpResponse getServerHttpResponse(ServerWebExchange exchange, DataBuffer requestBody) {

        final ServerWebExchange originalExchange = exchange;
        final ServerHttpResponse originalResponse = originalExchange.getResponse();

        return new ServerHttpResponseDecorator(originalResponse) {

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                Flux<DataBuffer> modifiedBody = Flux.from(body).buffer()
                        .map(dataBuffers -> {

                            DataBuffer responseBody = BufferUtil.join(dataBuffers);

                            return exchangeResponseBody(originalExchange, requestBody, responseBody);

                        });


                HttpHeaders httpHeaders = getDelegate().getHeaders();

                httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);

                return getDelegate().writeWith(modifiedBody);

            }

        };

    }

}

