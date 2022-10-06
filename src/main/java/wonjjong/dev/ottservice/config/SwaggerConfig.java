package wonjjong.dev.ottservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi adkApi() {
        return GroupedOpenApi.builder()
                .group("adk")
                .pathsToMatch("/adk/**")
                .build();
    }

    @Bean
    public GroupedOpenApi homeApi() {
        return GroupedOpenApi.builder()
                .group("home")
                .pathsToMatch("/home/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("API 타이틀")
                .description("API Description");

        return new OpenAPI()
                .info(info);
    }
}
