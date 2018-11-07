package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRecipeRepository implements RecipeRepository {
    private List<Recipe> recipeList = new ArrayList<>();

    public InMemoryRecipeRepository() {
        recipeList.add(new Recipe("Hashbrowns", 10));
        recipeList.add(new Recipe("Sandwich", 20));
        recipeList.add(new Recipe("Pizza", 30));
    }

    @Override
    public List<Recipe> findAll() {
        return recipeList;
    }

    @Override
    public boolean add(Recipe recipe) {
        return recipeList.add(recipe);
    }

    @Override
    public Recipe removeById(int id) {
        Recipe returnValue = recipeList.stream()
                .filter(recipe -> recipe.getId() == id).findFirst().orElse(null);
        if (returnValue != null) {
            recipeList = recipeList.stream()
                    .filter(recipe -> recipe.getId() != id).collect(Collectors.toList());
        }
        return returnValue;
    }

    @Override
    public Recipe updateValueById(int id, String title) {
        Recipe returnValue = recipeList.stream()
                .filter(recipe -> recipe.getId() == id).findFirst().orElse(null);
        if (returnValue != null) {
            returnValue.setTitle(title);
        }
        return returnValue;
    }
}
