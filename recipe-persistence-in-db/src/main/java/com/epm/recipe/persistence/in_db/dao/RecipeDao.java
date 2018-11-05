package com.epm.recipe.persistence.in_db.dao;

import com.epm.recipe.persistence.in_db.dto.RecipeDto;

import java.util.List;

public interface RecipeDao {
    List<RecipeDto> findAll();
    void add(RecipeDto recipeDto);
    RecipeDto getById(long id);
    void remove(long id);
    void update(RecipeDto recipeDto);
    void deleteAll();
}
