package br.edu.ifsul.cstsi.boa_acao.infra.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Boa Ação - Sistema Para Intermediar Doações")
                        .description("API REST para gestão de doadores, organizações parceiras e doações.")
                        .contact(new Contact()
                                .name("Paulo Rosa")
                                .email("paulorosa.pl013@academico.ifsul.edu.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://localhost:8080/licenca")));
    }
}