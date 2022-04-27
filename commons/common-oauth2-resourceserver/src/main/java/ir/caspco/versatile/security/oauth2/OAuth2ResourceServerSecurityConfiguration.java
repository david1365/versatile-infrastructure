package ir.caspco.versatile.security.oauth2;

import ir.caspco.versatile.common.util.swagger.SwaggerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import reactor.core.publisher.Mono;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableGlobalAuthentication
@EnableConfigurationProperties(OAuth2ResourceServerProperties.class)
public class OAuth2ResourceServerSecurityConfiguration {

    @Value("${security.oauth2.client.issuer-uri}")
    private String oidcIssuerLocation;

    @Value("${security.oauth2.client.registration.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.registration.client-secret}")
    private String clientSecret;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveClientRegistrationRepository clientRegistrationRepository) {

        // Authenticate through configured OpenID Provider
        http.oauth2Login();

        // Also logout at the OpenID Connect provider
        http.logout(logout -> logout.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository)));


        http.authorizeExchange().
                pathMatchers(SwaggerUtil.SWAGGER_WHITELIST).permitAll()
                .pathMatchers("/actuator/**").permitAll();


        http.authorizeExchange().anyExchange().authenticated();

        // Allow showing /home within a frame
        http.headers().frameOptions().mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);

        // Disable CSRF in the gateway to prevent conflicts with proxied service CSRF
        http.csrf().disable();

        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {

        return ReactiveJwtDecoders.fromOidcIssuerLocation(oidcIssuerLocation);
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrations() {

        ClientRegistration clientRegistration = ClientRegistrations
                .fromOidcIssuerLocation(oidcIssuerLocation)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

        return new InMemoryReactiveClientRegistrationRepository(clientRegistration);
    }

    @Bean
    public JwtDecoder jwtDecoderByIssuerUri(OAuth2ResourceServerProperties properties) {

        String issuerUri = properties.getJwt().getIssuerUri();
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);
        // Use preferred_username from claims as authentication name, instead of UUID subject
        jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());

        return jwtDecoder;
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Convert realm_access.roles claims to granted authorities, for use in access decisions
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}






