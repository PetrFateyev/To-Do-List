package ru.fateyev.ToDoList.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("To Do List Api")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Fateyev Petr")
                                )
                );
    }
}
