package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.jdbc.connectionPool.ConnectionProducer;
import com.epm.recipe.persistence.jdbc.connectionPool.HikariConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionPoolConfiguration {

    @Bean
    public ConnectionProducer connectionProducer(){
        return new HikariConnectionPool();
    }

}
