package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ResourceBundle;

@Component
public class InMemoryRecipeRepositoryTest {

    private InMemoryRecipeRepository recipeRepository;
    private EmbeddedDatabase h2;

    @BeforeEach
    public void beforeEach() {
        h2 = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("test-data.sql")
                .build();
        ResourceBundle properties = ResourceBundle.getBundle(("h2"));
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(properties.getString("url"));
        driverManagerDataSource.setUsername(properties.getString("username"));
        driverManagerDataSource.setPassword(properties.getString("password"));
        driverManagerDataSource.setDriverClassName(properties.getString("driver"));
        recipeRepository = new InMemoryRecipeRepository(driverManagerDataSource);
    }

    @AfterEach
    public void afterEach() {
        h2.shutdown();
    }

    @Test
    public void createRecipe() {
        //GIVEN
        String expectedResult = "zxc";
        //WHEN
        recipeRepository.createRecipe(new Recipe(expectedResult, -1));
        String actualResult = recipeRepository.findAll().get(2).title;
        //THEN
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAll() {
        //GIVEN
        Recipe recipe10 = new Recipe("qwerty", 1);
        Recipe recipe20 = new Recipe("uiop", 2);
        //WHEN
        List<Recipe> recipes = recipeRepository.findAll();
        //THEN
        Assertions.assertEquals(recipe10.title, recipes.get(0).title);
        Assertions.assertEquals(recipe20.title, recipes.get(1).title);
    }

    @Test
    public void updateRecipe() {
        //GIVEN
        String expectedResult = "asd";
        //WHEN
        recipeRepository.updateRecipe(new Recipe(expectedResult, 1));
        String actualResult = recipeRepository.findAll().get(0).title;
        //THEN
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteRecipe() {
        //GIVEN
        int expectedResult = 1;
        //WHEN
        recipeRepository.deleteRecipe(1);
        int actualResult = recipeRepository.findAll().size();
        //THEN
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
