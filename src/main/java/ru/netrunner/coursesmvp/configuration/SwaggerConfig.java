package ru.netrunner.coursesmvp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("NetRunner Courses")
                        .description("NetRunner Courses Backend API docs. Извиняюсь за уебанскую доку, бюджета на доку не хватило(( ")
                        .version("1.0").contact(new Contact().name("Bober").email("Ge0rgiusGus@yandex.ru")));
    }

}
