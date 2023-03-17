package com.knf.dev.demo.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class DBConfig {

    @Bean
    ConnectionFactoryInitializer initializer(
            ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer =
                new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(
                new ResourceDatabasePopulator(
                        new ClassPathResource("schema.sql")));
        return initializer;
    }
}
