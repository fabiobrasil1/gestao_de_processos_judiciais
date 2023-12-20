package com.ms.analise_tecnica_po;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication
@ComponentScan(basePackages = "com.ms.analise_tecnica_po")
@PropertySource("classpath:application.properties")
public class AnaliseTecnicaPoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaliseTecnicaPoApplication.class, args);
	}
}
