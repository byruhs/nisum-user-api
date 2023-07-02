package com.nisum.userapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(s -> !s.contains("/error"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "User Api",
                "Rest API de creacion de usuarios",
                "1.0.O",
                "",
                new Contact("Byron Delgado", "https://github.com/byruhs", "bdelgado90@outlook.com"),
                "MIT License",
                "https://opensource.org/licenses/mit-license.php",
                Collections.emptyList());
    }

}
