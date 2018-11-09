package com.epm.recipe.persistence.in_db.config;

import com.epm.recipe.persistence.in_db.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBManagerConfiguration {
    @Bean
    public DBManager dbManager(){
        return new DBManager();
    }
}
