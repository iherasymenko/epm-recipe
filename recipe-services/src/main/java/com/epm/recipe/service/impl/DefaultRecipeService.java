package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;
import java.util.Objects;
import java.util.List;

public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;

    public DefaultRecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
    }

    @Override
    public Recipe recipeOfTheDay() {
        List<Recipe> all = recipeRepository.findAll();
        return all.get(1);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findRecipeById(long id) {
        return recipeRepository.getById(id);
    }

    @Override
    public void createRecipe(Recipe recipe) {
        recipeRepository.insert(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipeRepository.update(recipe);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe.getId());
    }
}
