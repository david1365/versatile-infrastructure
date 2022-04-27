package ir.caspco.versatile.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${springfox.documentation.swagger-ui.host:localhost}")
    private String host;

    @Value("${springfox.documentation.swagger-ui.pathMapping:/gateway}")
    private String pathMapping;

    @Value("${springfox.documentation.swagger-ui.title:Rest Api}")
    private String title;

    @Value("${springfox.documentation.swagger-ui.description:Some custom description of API.}")
    private String description;

    @Value("${springfox.documentation.swagger-ui.version:1.0}")
    private String version;

    @Value("${springfox.documentation.swagger-ui.name:Davood Akbari}")
    private String name;

    @Value("${springfox.documentation.swagger-ui.email:daak1365@gmail.com}")
    private String email;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .host(host)
                .pathMapping(pathMapping)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfo(title,
                description,
                version,
                null,
                new Contact(name, null, email),
                null,
                null,
                Collections.emptyList());
    }

    private ApiKey apiKey() {

        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {

        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
