package br.com.brunocontente.crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Documentação API CRUD YOUTUBE")
                        .version("1.0.0")
                .description("Documentação da API CRUD do projeto de estudo de Spring Boot com banco de dados H2"));
    }
}
