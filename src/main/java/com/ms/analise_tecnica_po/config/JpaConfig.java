// package com.ms.analise_tecnica_po.config;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;

// import jakarta.persistence.EntityManagerFactory;

// import javax.sql.DataSource;

// @Configuration
// @EnableJpaRepositories(basePackages =
// "com.ms.analise_tecnica_po.repositories", entityManagerFactoryRef =
// "jpaSharedEM_entityManagerFactory", transactionManagerRef =
// "jpaSharedEM_transactionManager")
// public class JpaConfig {

// @Primary
// @Bean(name = "jpaSharedEM_dataSource")
// @ConfigurationProperties(prefix = "spring.datasource")
// public DataSource dataSource() {
// return DataSourceBuilder.create().build();
// }

// @Primary
// @Bean(name = "jpaSharedEM_entityManagerFactory")
// public LocalContainerEntityManagerFactoryBean entityManagerFactory(
// EntityManagerFactoryBuilder builder,
// @Qualifier("jpaSharedEM_dataSource") DataSource dataSource) {
// return builder
// .dataSource(dataSource)
// .packages("com.ms.analise_tecnica_po.models")
// .persistenceUnit("jpaSharedEM")
// .build();
// }

// @Primary
// @Bean(name = "jpaSharedEM_transactionManager")
// public PlatformTransactionManager transactionManager(
// @Qualifier("jpaSharedEM_entityManagerFactory") EntityManagerFactory
// entityManagerFactory) {
// return new JpaTransactionManager(entityManagerFactory);
// }
// }
