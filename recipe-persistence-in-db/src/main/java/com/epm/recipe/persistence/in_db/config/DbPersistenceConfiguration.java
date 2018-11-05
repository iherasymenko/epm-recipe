package com.epm.recipe.persistence.in_db.config;

import com.epm.recipe.persistence.in_db.DbRecipeRepository;
import com.epm.recipe.persistence.in_db.converter.Converter;
import com.epm.recipe.persistence.in_db.converter.RecipeConverter;
import com.epm.recipe.persistence.in_db.dao.RecipeDao;
import com.epm.recipe.persistence.in_db.dao.SpringJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DbPersistenceConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty("url"));
        driverManagerDataSource.setUsername(environment.getProperty("dbuser"));
        driverManagerDataSource.setPassword(environment.getProperty("dbpassword"));
        driverManagerDataSource.setDriverClassName(environment.getProperty("driver"));
        return driverManagerDataSource;
    }

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate JdbcTemplate() { return new JdbcTemplate(dataSource); }

    @Bean
    public RecipeConverter recipeConverter() {return new RecipeConverter();}

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public SpringJdbcDao springJdbcDao() { return new SpringJdbcDao(jdbcTemplate); }

    @Bean
    public DbRecipeRepository recipeRepository() { return new DbRecipeRepository();}

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
