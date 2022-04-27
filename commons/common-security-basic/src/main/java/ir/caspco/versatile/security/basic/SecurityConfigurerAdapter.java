package ir.caspco.versatile.security.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfigurerAdapter {

    @Value("${security.basic.username}")
    private String username;

    @Value("${security.basic.password}")
    private String password;

    @Value("${security.basic.roles}")
    private String[] roles;

    private final VersatileAuthenticationEntryPoint versatileAuthenticationEntryPoint;


    public SecurityConfigurerAdapter(VersatileAuthenticationEntryPoint versatileAuthenticationEntryPoint) {
        this.versatileAuthenticationEntryPoint = versatileAuthenticationEntryPoint;
    }


    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {

        return http
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .anonymous().disable()
                .httpBasic()
                .and()
                .exceptionHandling().authenticationEntryPoint(versatileAuthenticationEntryPoint)
                .and()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        UserDetails admin = User
                .withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles(roles)
                .build();

        return new MapReactiveUserDetailsService(admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}