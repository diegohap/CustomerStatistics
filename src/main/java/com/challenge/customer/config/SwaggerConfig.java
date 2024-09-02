package com.challenge.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Challenge-PinApp")
                        .version("0.1")
                        .description("Customer endpoints documentation")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
