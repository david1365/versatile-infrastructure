package ir.caspco.versatile.correlation;


import ir.caspco.versatile.common.util.security.HeaderUtil;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class CorrelationHeaderWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        String currentCorrelationId = HeaderUtil.resolveNamedValue(RequestCorrelation.X_CORRELATION_ID, serverWebExchange);

        if (currentCorrelationId == null) {

            UUID UUIDCurrentCorrelationId = UUID.randomUUID();
            currentCorrelationId = UUIDCurrentCorrelationId.toString();

            RequestCorrelation.setId(UUIDCurrentCorrelationId);

        } else {

            RequestCorrelation.setId(UUID.fromString(currentCorrelationId));
        }

        final ServerHttpRequest serverHttpRequest = serverWebExchange.getRequest().mutate()
                .header(RequestCorrelation.X_CORRELATION_ID, currentCorrelationId)
                .build();

        return webFilterChain.filter(serverWebExchange.mutate().request(serverHttpRequest).build());

    }

}
