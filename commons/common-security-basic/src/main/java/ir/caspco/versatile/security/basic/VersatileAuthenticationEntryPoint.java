package ir.caspco.versatile.security.basic;

import ir.caspco.versatile.context.handler.exceptions.HttpUnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@Slf4j
public class VersatileAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException authenticationException) {

        log.error("Responding with unauthorized error. Message - {}", authenticationException.getMessage());

        throw new HttpUnauthorizedException();

    }

}