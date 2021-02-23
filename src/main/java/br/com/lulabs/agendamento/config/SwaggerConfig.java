package br.com.lulabs.agendamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.lulabs.agendamento.controller"))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Forbidden!")
                    .build());
        }};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Luizalabs Mensagens")
                .description("Desafio de conhecimento técnico para processo seletivo Luizalabs. Plataforma de comunicação com agendamento de mensagens, consulta e cancelamento de envio de mensagens através de uma API utilizando o modelo RESTful utilizando Springboot e PostgreSQL")
                .version("1.0.10")
                .license("The Unlicensed")
                .licenseUrl("http://unlicense.org/")
                        .contact(new Contact("Daniel Tief", "www.linkedin.com/in/tiefenbarher\n", "chinorex@gmail.com"))
                        .build();
    }
}
