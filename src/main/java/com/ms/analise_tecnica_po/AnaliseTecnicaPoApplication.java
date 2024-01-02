package com.ms.analise_tecnica_po;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan(basePackages = "com.ms.analise_tecnica_po")
// @ComponentScan(basePackages =
// "com.ms.analise_tecnica_po.domain.user.repositories")
@PropertySource("classpath:application.properties")
@OpenAPIDefinition(info = @Info(title = "Analise Tecnica P.O", version = "1", description = "Gerenciamento de Processos"))
public class AnaliseTecnicaPoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaliseTecnicaPoApplication.class, args);
	}
}
