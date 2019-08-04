package com.epm.recipe.persistence.jdbc.config;


import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${db.url}") String url,
                                 @Value("${db.user}") String user,
                                 @Value("${db.password}") String password
                                 ) throws SQLException

    {
        MariaDbDataSource ds = new MariaDbDataSource();
        ds.setUrl(url);
        ds.setUser(user);
        ds.setPassword(password);

        return ds;
    }

    @Bean
    public RecipeRepository recipeRepository(DataSource ds) {
        return new JdbcRecipeRepository(ds);
    }
}
