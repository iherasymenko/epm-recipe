package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;
import java.util.List;

public interface RecipeRepository {

    List<Recipe> findAll();

    Recipe findOneById(Long id);

    void create(Recipe recipe);

    void update(Recipe recipe);

    void delete(Recipe recipe);
}
