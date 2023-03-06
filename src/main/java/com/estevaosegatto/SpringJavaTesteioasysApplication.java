package com.estevaosegatto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "IMDb API", version = "1.0", description = "Informações Gerais - Usuários e Filmes"))
@SecurityScheme(name = "Autenticador", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "Bearer [token]", 
				description = "Authenticação JWT com bearer token (Necessário apenas o token)", in = SecuritySchemeIn.HEADER)
public class SpringJavaTesteioasysApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringJavaTesteioasysApplication.class, args);
	}
}
