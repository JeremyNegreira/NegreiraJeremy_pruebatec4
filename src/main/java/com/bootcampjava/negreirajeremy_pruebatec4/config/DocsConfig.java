package com.bootcampjava.negreirajeremy_pruebatec4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Documentación de la Prueba Técnica Nº 4 - Spring Boot")
                .version("v1")
                .description("A continuación se muestra la documentación de los endpoints de la cuarta prueba técnica:"));
    }

}
