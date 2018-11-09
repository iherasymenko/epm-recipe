package com.epm.recipe.persistence.db.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.db.JdbcRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@ComponentScan({ "com.epm.recipe.persistence.db" })
@Configuration

//@PropertySource("classpath:/jdbc.properties")
public class JdbcPersistenceConfiguration {

    @Autowired
    DataSource dataSource;

//    @Autowired
//    Environment environment;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/recipe_db");
        dataSource.setUsername("root");
        dataSource.setPassword("root");


//        dataSource.setDriverClassName(environment.getProperty("driver"));
//        dataSource.setUrl(environment.getProperty("url"));
//        dataSource.setUsername(environment.getProperty("user"));
//        dataSource.setPassword(environment.getProperty("password"));


        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public RecipeRepository recipeRepository() {
        return new JdbcRecipeRepository();
    }


}
