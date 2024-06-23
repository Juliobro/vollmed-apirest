package med.voll.vollapirest.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Voll.med")
                        .description("API Rest de la aplicación Voll.med que contiene las funcionalidades de CRUD de médicos y pacientes así como programación, cancelación y vista de consultas. Visita el primer link de abajo para ver el repositorio en GitHub y acceder a instrucciones de uso")
                        .contact(new Contact()
                                .name("Repositorio GitHub")
                                .url("https://github.com/Juliobro/vollmed-apirest"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Juliobro/vollmed-apirest/blob/main/LICENSE.md")));
    }
}
