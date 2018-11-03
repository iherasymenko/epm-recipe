package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    private List<Recipe> recipeList;

    public InMemoryRecipeRepository() {
        recipeList = new LinkedList<>();
        recipeList.add(new Recipe("Hashbrown", 1));
        recipeList.add(new Recipe("Sandwich", 2));
        //Arrays.asList(new Recipe("Hashbrown", 10), new Recipe("Sandwich", 20));
    }

    @Override
    public List<Recipe> findAll() {
        return recipeList;
    }

    @Override
    public void add(Recipe recipe) {
        recipe.setId(recipeList.size() + 1);
        recipeList.add(recipe);
    }

    @Override
    public Recipe getById(long id) {
        return recipeList.stream()
                .filter(recipe -> (recipe.getId() == id))
                .findFirst()
                .get();

    }

    @Override
    public void deleteById(long id) {
        recipeList.remove(getById(id));
    }

    @Override
    public void update(Recipe recipe) {
        deleteById(recipe.getId());
        add(recipe);
    }
}
