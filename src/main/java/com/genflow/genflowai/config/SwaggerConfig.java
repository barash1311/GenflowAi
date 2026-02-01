package com.genflow.genflowai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI genflowOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GenFlow AI Backend APIs")
                        .description("Backend APIs for GenFlow AI – datasets, prompts, predictions, and jobs")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("GenFlow AI Team")
                                .email("barash1311@gmail.com")
                        )
                );
    }
}
