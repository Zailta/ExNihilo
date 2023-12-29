package com.blog.application.Configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
		title = "Ex-Nihilo",
		description = "Blogging API",
		contact = @Contact(
				name = "Admin",
				email ="admin@exnihoilo.dev"),
		version = "V1.0"
		))
@Configuration
public class EXNOpenAPIConfig {


}
