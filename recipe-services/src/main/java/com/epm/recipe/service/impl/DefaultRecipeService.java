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
            throw new IllegalStateException("No recipes at all");
        }
        return all.get(0);
    }

    @Override
    public Optional<Recipe> byId(long id) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipe -> recipe.getId() == id)
                .findFirst();
    }

    @Override
    public Recipe findById(long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public boolean create(Recipe recipe) {
        return recipeRepository.create(recipe);
    }

    @Override
    public boolean update(Recipe recipe) {
        return recipeRepository.update(recipe);
    }

    @Override
    public boolean delete(long id) {
        return recipeRepository.delete(id);
    }

}
