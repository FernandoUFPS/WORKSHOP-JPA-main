package com.remolina.workshop.configurer;

import com.remolina.workshop.controller.CitaController;
import com.remolina.workshop.repository.CitaRepository;
import com.remolina.workshop.service.CitaMService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.sql.DriverManager;
import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan({"com.remolina"})
@EnableJpaRepositories(basePackages = "com.remolina", entityManagerFactoryRef = "entityManagerFactory")
@EntityScan(basePackages = "com.remolina")
public class AppConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public CitaMService citaMService(CitaRepository citaRepository) {
        return new CitaMService(citaRepository);
    }

    @Bean
    public CitaController citaController(CitaMService citaMService) {
        return new CitaController(citaMService);
    }



}

