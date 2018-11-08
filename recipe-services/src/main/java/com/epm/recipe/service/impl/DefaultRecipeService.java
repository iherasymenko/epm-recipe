package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;

    public DefaultRecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
    }

    @Override
    public Recipe recipeOfTheDay() {
        List<Recipe> all = recipeRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalStateException("No recipes at all");
        }
        return all.get(0);
    }

    @Override
    public Optional<Recipe> readRecipe(long id) {
        return recipeRepository.read(id);
    }

    @Override
    public void createRecipe(String title) {
        recipeRepository.createRecipe(title);
    }

    @Override
    public void updateRecipe(long id, String title) {
        recipeRepository.updateRecipe(id, title);
    }

    @Override
    public void deleteRecipe(long id) {
        recipeRepository.deleteRecipe(id);
    }

    @Override
    public List<Recipe> readAll() {
        List<Recipe> all = recipeRepository.findAll();
        all.sort(Comparator.comparingLong(value -> value.id));
        return all;
    }

}
