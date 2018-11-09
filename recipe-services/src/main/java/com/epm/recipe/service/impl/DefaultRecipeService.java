package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;

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
            all.add(new Recipe("<Error: the receipt is absent>", -1));
        }
        return all.get(0);
    }

    @Override
    public Optional<Recipe> byId(long id) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipe -> recipe.id == id)
                .findFirst();
    }

    @Override
    public boolean createRecipe(Recipe recipe) {
        return recipeRepository.createRecipe(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public boolean updateRecipe(Recipe recipe) {
        return recipeRepository.updateRecipe(recipe);
    }

    @Override
    public boolean deleteRecipe(long id) {
        return recipeRepository.deleteRecipe(id);
    }
}
