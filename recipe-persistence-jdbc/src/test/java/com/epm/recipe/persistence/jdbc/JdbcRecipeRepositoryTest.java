package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.exceptions.RepositoryException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JdbcRecipeRepositoryTest {
    private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
    private RecipeRepository recipeRepositiory = new JdbcRecipeRepository(jdbcTemplate);

    @Test
    public void findingAllRecipes() {
        //GIVEN
        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new Recipe("title", 1));
        expectedRecipes.add(new Recipe("title2", 2));

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class)
        )).thenReturn(expectedRecipes);
        //WHEN
        List<Recipe> actualRecipes = recipeRepositiory.findAll();
        //THEN
        assertSame(expectedRecipes, actualRecipes);
    }

    @Test
    public void findingAllRecipesWhenThereAreNo() {
        //GIVEN
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class)
        )).thenReturn(Collections.emptyList());
        //WHEN THEN
        assertThrows(RepositoryException.class,
                () -> {
                    recipeRepositiory.findAll();
                },
                "There is no recipes");
    }

    @Test
    public void findingRecipeById() {
        //GIVEN
        Recipe expectedRecipe = new Recipe("title", 1);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(expectedRecipe);
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(recipes);
        //WHEN
        Recipe actualRecipe = recipeRepositiory.findById(1);
        //THEN
        assertSame(expectedRecipe, actualRecipe);
    }

    @Test
    public void findingRecipeByIdWhenThereIsNo() {
        //GIVEN
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(Collections.emptyList());
        //WHEN THEN
        assertThrows(RepositoryException.class,
                () -> {
                    recipeRepositiory.findById(1);
                },
                "There is no recipes.");
    }

    @Test
    public void updatingRecipe() {
        //GIVEN
        Recipe updatedRecipe = new Recipe("title", 1);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(updatedRecipe);
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(recipes);
        //WHEN
        recipeRepositiory.update(updatedRecipe);
        //THEN
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq("title"),
                eq((long) 1)
        );
    }

    @Test
    public void updatingUnexistingRecipe() {
        //GIVEN
        Recipe updatedRecipe = new Recipe("title", 1);
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(Collections.emptyList());
        //WHEN THEN
        assertThrows(RepositoryException.class,
                () -> {
                    recipeRepositiory.update(updatedRecipe);
                },
                "There is no recipe with id = 1");
        verify(jdbcTemplate, times(0)).update(
                anyString(),
                eq("title"),
                eq((long) 1)
        );
    }

    @Test
    public void deletingRecipe() {
        //GIVEN
        Recipe deletedRecipe = new Recipe("title", 1);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(deletedRecipe);
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(recipes);
        //WHEN
        recipeRepositiory.delete(1);
        //THEN
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq((long) 1)
        );
    }

    @Test
    public void deletingUnexistingRecipe(){
        //GIVEN
        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq((long) 1)
        )).thenReturn(Collections.emptyList());
        //WHEN THEN
        assertThrows(RepositoryException.class,
                () -> {
                    recipeRepositiory.delete(1);
                },
                "There is no recipe with id = 1");
        verify(jdbcTemplate, times(0)).update(
                anyString(),
                eq((long) 1)
        );
    }

    @Test
    public void creatingRecipe(){
        //GIVEN
        Recipe createdRecipe = new Recipe("title", 0);
        //WHEN
        recipeRepositiory.create(createdRecipe);
        //THEN
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq("title")
        );
    }
}
