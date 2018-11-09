package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("recipeService")
@Repository
    public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
    }


    @Override
    public Recipe recipeOfTheDay() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (recipes.isEmpty()) {
            throw new IllegalStateException("No recipes at all");
        }
        return recipes.get(0);
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        return  recipes;
    }

    @Override
    public Optional<Recipe> findById(long id) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipe -> recipe.id == id)
                .findFirst();
    }

    @Override
    public void create(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
     }

    @Override
    public void delete(long id) {
        recipeRepository.delete(id);
    }
}
