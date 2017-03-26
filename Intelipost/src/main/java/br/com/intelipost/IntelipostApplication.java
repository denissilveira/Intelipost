package br.com.intelipost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan({"br.com.intelipost"})
public class IntelipostApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelipostApplication.class, args);
	}
	
	@Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .select()
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            .build();
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Intelipost API's")
            .description("description")
            .contact(new Contact("Denis Silveira", "https://www.intelipost.com.br/", "denis.rayan@gmail.com"))
            .license("Apache License Version 2.0")
            .version("2.0")
            .build();
    }
}