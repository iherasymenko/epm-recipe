package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.in_memory.InMemoryRecipeRepository;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import(BoneCpDBConfiguration.class)
public class InMemoryPersistenceConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public InMemoryRecipeRepository recipeRepository(JdbcTemplate jdbcTemplate) {
        return new InMemoryRecipeRepository(jdbcTemplate);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource){
        SpringLiquibase liquiBase = new SpringLiquibase();
        liquiBase.setChangeLog("classpath:db-changelog-master.xml");
        liquiBase.setDataSource(dataSource);
        return liquiBase;
    }

}
