package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.domain.Restaurant;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
    Recipe getById(int id);
    List<Recipe> getByRestaurant(Restaurant restaurant);
    void setup();
}
