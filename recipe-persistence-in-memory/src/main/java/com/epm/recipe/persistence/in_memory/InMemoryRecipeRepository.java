package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryRecipeRepository implements RecipeRepository {

    @Override
    public List<Recipe> findAllRecipes() {
        return List.of(
                new Recipe("Hashbrowns", 1L), new Recipe("Strawberry Cheesecake", 2L),
                new Recipe("Chicken Tacos", 3L), new Recipe("Crispy Hot Honey Chicken", 4L),
                new Recipe("Chorizo Penne", 5L), new Recipe("Toasty Pesto Gnocchi", 6L),
                new Recipe("Sausage Pizza", 7L), new Recipe("Peanut Satay Chicken", 8L),
                new Recipe("Sweet Yakisoba Noodles", 9L), new Recipe("Thai Basil Stir-Fry", 10L),
                new Recipe("Pork Luau Burgers", 11L), new Recipe("Greek Diner Burgers on the Grill", 12L),
                new Recipe("Greek Salad Flatbreads", 13L), new Recipe("Chicken Gyro Salad", 14L),
                new Recipe("Korean Beef Bibimbap", 15L), new Recipe("Ground Beef Bulgogi Bowls", 16L),
                new Recipe("Sesame Honey Beef Stir-Fry", 17L), new Recipe("Kimchi Hot Sauce Burritos", 18L),
                new Recipe("Chipotle-Spiced Steak", 19L), new Recipe("Pork Risotto Casserole", 20L)
        );
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return findAllRecipes().stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertRecipe(Recipe recipe) {
    }

    @Override
    public void updateRecipe(Long id, Recipe recipe) {
        recipe.setTitle("new recipe");
    }

    @Override
    public void deleteRecipe(Long id) {
        findAllRecipes().removeIf(recipe -> recipe.getId().equals(id));
    }
}
