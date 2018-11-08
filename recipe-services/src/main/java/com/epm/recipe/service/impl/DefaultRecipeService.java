package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;

import java.sql.SQLException;
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
    public List<Recipe> allInDB() throws SQLException {
        return recipeRepository.getAll();
    }


    @Override
    public Recipe byIdInDB(long id) throws SQLException {
        return recipeRepository.getRecipe(id);
    }

    @Override
    public long Create(Recipe recipe) {
        return recipeRepository.Create(recipe);
    }

    @Override
    public void Update(Recipe recipe, long id) {
        recipeRepository.Update(recipe, id);
    }

    @Override
    public void Delete(long id){
        recipeRepository.Delete(id);
    }

}
