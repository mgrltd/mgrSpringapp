package com.mgr.MgrSpringApp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MgrSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MgrSpringAppApplication.class, args);
	}

	
	private ApiKey apikeys() {
        return  new ApiKey("JWT", "Authorization", "header");
        }
	
	private SecurityContext securityContexts() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 

    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
    }
	 
	 private ApiInfo getinfo() {
	        return new ApiInfo(
	                "MGR",//main header
	                "developed  by:",//header
	                "1.0", // version
	                //body links 
	                "Terms and Services",
	                new Contact("pavan", "https://www.google.in", "pavan@gmail.com"),// name ,web link, contact mail
	                "License of Api", "api license url",
	                Collections.emptyList()
	                );
	    }
	
@Bean 
public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getinfo())
        .securityContexts(Arrays.asList(securityContexts()))
        .securitySchemes(Arrays.asList(apikeys()))
        .select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build();
        } 

}



