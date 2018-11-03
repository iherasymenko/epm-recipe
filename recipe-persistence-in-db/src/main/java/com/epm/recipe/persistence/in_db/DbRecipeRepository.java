package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DbRecipeRepository implements RecipeRepository {
    private List<Recipe> recipeList;

    public DbRecipeRepository() {
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
    public Optional<Recipe> getById(long id) {
        return recipeList.stream()
                .filter(recipe -> (recipe.getId() == id))
                .findFirst();
    }

    @Override
    public void deleteById(long id) {
        Recipe recipe = getById(id).get();
        recipeList.remove(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        getById(recipe.getId()).get().setTitle(recipe.getTitle());
    }
}
