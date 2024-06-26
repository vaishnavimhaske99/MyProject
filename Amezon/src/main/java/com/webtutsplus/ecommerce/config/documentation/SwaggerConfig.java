package com.webtutsplus.ecommerce.config.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.webtutsplus.ecommerce"))
                .paths(PathSelectors.any())
                .build();
    }
	
	private ApiInfo getApiInfo() {
        Contact contact = new Contact("AmezoneStatus","http://Amezone.com", "vaishnavimhaske29@gmail.com");
        return new ApiInfoBuilder()
                .title("Amezone Backend")
                .description("Documentation Ecommerce api Devoloped by Vaishnavi Mhaske")
                .version("1.0.0")
                .license("")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}