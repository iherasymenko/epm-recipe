package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRecipeRepository implements RecipeRepository {

    private static List<Recipe> recipes = new ArrayList<>();
    private static AtomicLong counter = new AtomicLong(100);
    static {
        recipes.addAll(List.of(
                new Recipe("Hashbrowns", 10),
                new Recipe("Sandwich", 20)
        ));
    }

    @Override
    public List<Recipe> findAll() {
        return Collections.unmodifiableList(recipes);
    }

    @Override
    public Optional<Recipe> read(long id) {
        return recipes.stream().filter(recipe -> recipe.id == id).findFirst();
    }

    @Override
    public void createRecipe(String title) {
        recipes.add(new Recipe(title, counter.getAndIncrement()));
    }

    @Override
    public void updateRecipe(long id, String title) {
        for (int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).id == id){
                recipes.set(i, new Recipe(title, id));
                break;
            }
        }
    }

    @Override
    public void deleteRecipe(long id) {
        for (int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).id == id){
                recipes.remove(i);
                break;
            }
        }
    }
}
