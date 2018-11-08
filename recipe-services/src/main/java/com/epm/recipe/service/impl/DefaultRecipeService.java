package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultRecipeService implements RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRecipeService.class);
    private final RecipeRepository recipeRepository;

    public DefaultRecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
    }

    @Override
    public Optional<Recipe> recipeOfTheDay() {
        LOGGER.info("Getting the recipe of the day...");
        return recipeRepository.getRecipeById(1L);
    }


    @Override
    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAllRecipes();
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.getRecipeById(id);
    }

    @Override
    public void insertRecipe(Recipe recipe) {
        recipeRepository.insertRecipe(recipe);
    }

    @Override
    public void updateRecipe(Long id, Recipe recipe) {
        recipeRepository.updateRecipe(id, recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteRecipe(id);
    }
}
