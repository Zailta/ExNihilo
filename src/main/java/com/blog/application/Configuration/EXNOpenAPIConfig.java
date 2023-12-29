package com.blog.application.Configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(
		title = "Ex-Nihilo",
		description = "Blogging API",
		contact = @Contact(
				name = "Admin",
				email ="admin@exnihoilo.dev"),
		version = "V1.0"
		
		))
@SecurityScheme(name = "authBearer", 
		in = SecuritySchemeIn.HEADER, 
		type = SecuritySchemeType.HTTP, 
		bearerFormat = "JWT", 
		scheme = "Bearer",
		description = "Need Token to access")
@Configuration
public class EXNOpenAPIConfig {


}
