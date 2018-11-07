package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    private ArrayList<Recipe> recipeList;


    public InMemoryRecipeRepository() {
        recipeList = new ArrayList<>();
        recipeList.add(new Recipe(1, "Hashbrown"));
        recipeList.add(new Recipe(2, "Sandwich"));
    }

    @Override
    public List<Recipe> findAll() {
        return recipeList;
    }

    @Override
    public Recipe getById(long id) {
        return recipeList.stream()
                .filter(recipe -> (recipe.getId() == id))
                .findFirst()
                .get();
    }

    @Override
    public void insert(Recipe recipe) {
        recipe.setId(recipeList.get(recipeList.size()-1).getId() + 1);
        recipeList.add(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        getById(recipe.getId()).setTitle(recipe.getTitle());
    }

    @Override
    public void delete(long id) {
        recipeList.remove(getById(id));
    }
}
