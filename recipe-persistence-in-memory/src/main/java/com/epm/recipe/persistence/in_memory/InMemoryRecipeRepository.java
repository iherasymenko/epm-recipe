package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryRecipeRepository implements RecipeRepository {
    @Override
    public List<Recipe> findAll() {
        return List.of(
                new Recipe("Hash Browns", 10),
                new Recipe("Sandwich", 20)
        );
    }

    @Override
    public Recipe findById(long id) {
        return new Recipe("Default recipe", id);
    }
}
