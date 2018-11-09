package com.epm.recipe.persistence.db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

class JdbcRecipeRepositoryTest {
    private RecipeRepository repository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcPersistenceConfiguration.class);
        repository = context.getBean("recipeRepository", JdbcRecipeRepository.class);

    }

    @org.junit.jupiter.api.Test
    void findAll() {
        //Give
        Recipe recipe10 = new Recipe("Hashbrowns", 10L);
        Recipe recipe20 = new Recipe("Sandwich", 20L);
        int expectedSize = 2;
        //When
        List<Recipe> recipes = repository.findAll();
        //Then
        Assertions.assertEquals(expectedSize, recipes.size());
        Assertions.assertEquals(recipe10.id, recipes.get(0).id);
        Assertions.assertEquals(recipe10.title, recipes.get(0).title);
        Assertions.assertEquals(recipe20.id, recipes.get(1).id);
        Assertions.assertEquals(recipe20.title, recipes.get(1).title);
    }

    @org.junit.jupiter.api.Test
    void getById() {
        //Give
        Recipe expectedRecipe= new Recipe("Hashbrowns", 10L);
        //When
        Recipe recipe = repository.getById(10L);
        //Then
        Assertions.assertEquals(expectedRecipe.title, recipe.title);
    }

    @org.junit.jupiter.api.Test
    void save() {
        //Give
        Recipe recipe30 = new Recipe("Soup", 30L);
        int expectedSize = 3;
        //When
        repository.save(recipe30);
        List<Recipe> recipes = repository.findAll();
        //Then
        Assertions.assertEquals(expectedSize, recipes.size());
        Assertions.assertEquals(recipe30.id, recipes.get(2).id);
        Assertions.assertEquals(recipe30.title, recipes.get(2).title);

    }


    @org.junit.jupiter.api.Test
    void update() {
        //Give
        String  apple = "apple";
        //When
        repository.update(new Recipe(apple,10L));
        //Then
        Assertions.assertEquals(apple, repository.getById(10L).title);

    }

    @org.junit.jupiter.api.Test
    void delete() {
        //Give
        List<Recipe> recipes = repository.findAll();
        int oldSize = recipes.size();
        //When
        repository.delete(10L);
        //Then
        Assertions.assertEquals(oldSize-1, repository.findAll().size());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> repository.getById(10L));
    }

}