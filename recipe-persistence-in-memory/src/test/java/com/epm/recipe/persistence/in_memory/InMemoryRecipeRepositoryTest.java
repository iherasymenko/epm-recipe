package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class InMemoryRecipeRepositoryTest {

    private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
    private RecipeRepository recipeRepository = new InMemoryRecipeRepository(jdbcTemplate);

    @Test
    public void findRecipeById() {
        //GIVEN
        Recipe expected = new Recipe("first", 1);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(expected);
        //WHEN
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq((long) 1))).thenReturn(recipes);
        Recipe actual = recipeRepository.findById(1);
        //THEN
        assertSame(expected, actual);
    }

    @Test
    public void findAllRecipes() {
        //GIVEN
        List<Recipe> expected = new ArrayList<>();
        expected.add(new Recipe("first", 1));
        expected.add(new Recipe("second", 2));
        expected.add(new Recipe("third", 3));
        //WHEN
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);
        List<Recipe> actual = recipeRepository.findAll();
        //THEN
        assertSame(expected, actual);
    }
}
