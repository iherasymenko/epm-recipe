package com.epm.recipe.persistence.in_db.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


//@Configuration
//@ComponentScan("com.epm.recipe.*")
//@PropertySource("classpath:database.properties")
public class DbConfig {

//    @Autowired
//    Environment environment;
//
//
//    @Autowired
//    ApplicationContext applicationContext;

//    private final String URL = "url";
//    private final String USER = "dbuser";
//    private final String DRIVER = "driver";
//    private final String PASSWORD = "dbpassword";

//    @Bean
    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClassName("com.h2.Driver");
        dataSource.setDriver(new org.h2.Driver());
        dataSource.setUrl("jdbc:h2:~/recipeDb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=1;MODE=Oracle");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

//        environment = applicationContext.getEnvironment();
//        driverManagerDataSource.setUrl(environment.getProperty(URL));
//        driverManagerDataSource.setUsername(environment.getProperty(USER));
//        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
//        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));

        return dataSource;
    }

//    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

//    @Bean
    public RecipeDao RecipeDao(){
        SpringJdbcDao springJdbcDao = new SpringJdbcDao();
        springJdbcDao.setJdbcTemplate(jdbcTemplate());
        return springJdbcDao;
    }
}
