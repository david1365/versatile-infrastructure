package ir.caspco.versatile.global.log.filter;


import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.correlation.RequestCorrelation;
import ir.caspco.versatile.gateway.smart.filters.cash.CashBuilder;
import ir.caspco.versatile.gateway.smart.filters.cash.DCash;
import ir.caspco.versatile.global.log.domains.GlobalAPILogEntity;
import ir.caspco.versatile.global.log.repositories.GlobalAPILogRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


//TODO from davood akbari: Do not forget to test.
@Slf4j
@Component
public class GlobalAPILogWebFilter implements WebFilter {

    private final CashBuilder<GlobalAPILogEntity> cashBuilder;

    private final GlobalAPILogRepository globalAPILogRepository;


    public GlobalAPILogWebFilter(CashBuilder<GlobalAPILogEntity> cashBuilder,
                                 GlobalAPILogRepository globalAPILogRepository) {

        this.cashBuilder = cashBuilder;
        this.globalAPILogRepository = globalAPILogRepository;
    }


    private static final Set<String> LOGGABLE_CONTENT_TYPES = new HashSet<>(Arrays.asList(
            MediaType.APPLICATION_JSON_VALUE.toLowerCase(),
            MediaType.APPLICATION_JSON_UTF8_VALUE.toLowerCase(),
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.TEXT_XML_VALUE
    ));


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        DCash<GlobalAPILogEntity> cash = cashBuilder.build(exchange);

        ServerHttpRequest requestMutated = getServerHttpRequest(exchange, cash);

        ServerHttpResponse responseMutated = getServerHttpResponse(exchange, cash);

        return chain.filter(exchange.mutate().request(requestMutated).response(responseMutated).build());

    }

    private ServerHttpResponse getServerHttpResponse(ServerWebExchange exchange, DCash<GlobalAPILogEntity> cash) {

        return new ServerHttpResponseDecorator(exchange.getResponse()) {

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                Logger responseLogger = new Logger(getDelegate(), cash, globalAPILogRepository);

                if (LOGGABLE_CONTENT_TYPES.contains(String.valueOf(getHeaders().getContentType()).toLowerCase())) {

                    return join(body).flatMap(db -> {

                        responseLogger.body(db.asByteBuffer());
                        responseLogger.log();

                        return getDelegate().writeWith(Mono.just(db));

                    });
                } else {

                    responseLogger.log();
                    return getDelegate().writeWith(body);

                }
            }

        };
    }

    private ServerHttpRequest getServerHttpRequest(ServerWebExchange exchange, DCash<GlobalAPILogEntity> cash) {

        Logger requestLogger = new Logger(exchange.getRequest(), cash, globalAPILogRepository);

        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {

                if (LOGGABLE_CONTENT_TYPES.contains(String.valueOf(getHeaders().getContentType()).toLowerCase())) {

                    return super.getBody().map(ds -> {

                        requestLogger.body(ds.asByteBuffer());
                        return ds;

                    }).doFinally((s) -> requestLogger.log());

                } else {

                    requestLogger.log();
                    return super.getBody();

                }
            }

        };
    }

    private Mono<? extends DataBuffer> join(Publisher<? extends DataBuffer> body) {

        Assert.notNull(body, "'dataBuffers' must not be null");

        return Flux.from(body)
                .collectList()
                .filter((list) -> !list.isEmpty())
                .map((list) -> list.get(0).factory().join(list))
                .doOnDiscard(PooledDataBuffer.class, DataBufferUtils::release);

    }

    private static class Logger {

        private final StringBuilder stringBuilder = new StringBuilder();
        private final GlobalAPILogRepository globalAPILogRepository;
        private final GlobalAPILogEntity globalAPILogEntity;
        private final boolean isRequest;


        Logger(ServerHttpResponse response, DCash<GlobalAPILogEntity> cash, GlobalAPILogRepository globalAPILogRepository) {

            this.globalAPILogRepository = globalAPILogRepository;
            this.globalAPILogEntity = cash.get();
            this.isRequest = false;

            RequestCorrelation.setId(this.globalAPILogEntity.getCorrelationId());

            System.out.println("Thread :" + Thread.currentThread().getName() + "-------------------------------------");

            final String headers = getHeaders(response.getHeaders());
            final HttpStatus statusCode = response.getStatusCode();


            globalAPILogEntity.setResponseHeaders(headers);
            globalAPILogEntity.setStatus(statusCode);

            stringBuilder.append("\n");
            stringBuilder.append("---- Response -----").append("\n");
            stringBuilder.append("Headers      :").append(headers).append("\n");

            stringBuilder.append("Status code  :").append(statusCode).append("\n");

        }

        Logger(ServerHttpRequest request, DCash<GlobalAPILogEntity> cash, GlobalAPILogRepository globalAPILogRepository) {

            this.globalAPILogRepository = globalAPILogRepository;
            this.isRequest = true;

            final String headers = getHeaders(request.getHeaders());
            final HttpMethod method = request.getMethod();
            final InetSocketAddress remoteAddress = request.getRemoteAddress();
            final String requestPath = request.getPath().pathWithinApplication().value();

            System.out.println("Thread :" + Thread.currentThread().getName() + "-------------------------------------");

            this.globalAPILogEntity = GlobalAPILogEntity.builder()
                    .requestPath(requestPath)
                    .requestHeaders(headers)
                    .httpMethod(method)
                    .build();

            if (remoteAddress != null) {
                globalAPILogEntity.setRemoteAddress(remoteAddress.toString());
            }

            cash.put(globalAPILogEntity);

            stringBuilder.append("\n");
            stringBuilder.append("---- Request -----").append("\n");
            stringBuilder.append("Request Path      :").append(requestPath).append("\n");
            stringBuilder.append("Headers           :").append(headers).append("\n");
            stringBuilder.append("Method            :").append(method).append("\n");
            stringBuilder.append("Client            :").append(remoteAddress).append("\n");

            if (HttpMethod.DELETE.equals(method) || HttpMethod.GET.equals(method)) {
                log();
            }

        }

        void body(ByteBuffer byteBuffer) {

            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
            String jsonBody = charBuffer.toString();

            if (isRequest) {
                globalAPILogEntity.setRequestBody(jsonBody);
            } else {
                globalAPILogEntity.setResponseBody(jsonBody);
            }

            stringBuilder.append("Body         :").append(charBuffer).append("\n");

        }

        void log() {

            globalAPILogRepository.save(globalAPILogEntity);

            stringBuilder.append("-------------------").append("\n");

            log.debug(stringBuilder.toString());

        }

        private String getHeaders(HttpHeaders headers) {
            return Shift.just(headers.toSingleValueMap()).toJson();
        }

    }
}
