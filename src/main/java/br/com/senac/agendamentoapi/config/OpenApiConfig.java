package br.com.senac.agendamentoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI agendamentoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Agendamento API")
                        .description("API REST para CRUD de clientes, funcionários, serviços, horários disponíveis e agendamentos.")
                        .version("1.0.0")
                        .contact(new Contact().name("Senac")));
    }
}
