package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_db.converter.Converter;
import com.epm.recipe.persistence.in_db.converter.RecipeConverter;
import com.epm.recipe.persistence.in_db.dao.SpringJdbcDao;
import com.epm.recipe.persistence.in_db.dto.RecipeDto;

import java.util.LinkedList;
import java.util.List;

public class DbRecipeRepository implements RecipeRepository {
    private List<Recipe> recipeList;

    SpringJdbcDao springJdbcDao;
    Converter recipeConverter;

    public DbRecipeRepository() {
        springJdbcDao = new SpringJdbcDao();
        recipeConverter = new RecipeConverter();
    }

    @Override
    public List<Recipe> findAll() {
        return recipeConverter.asObj(springJdbcDao.findAll());
    }

    @Override
    public void add(Recipe recipe) {
        springJdbcDao.add((RecipeDto)recipeConverter.asDto(recipe));
    }

    @Override
    public Recipe getById(long id) {
        Recipe recipe = (Recipe) recipeConverter.asObj(springJdbcDao.getById(id));
        return recipe;
    }

    @Override
    public void deleteById(long id) {
//        Recipe recipe = getById(id);
        springJdbcDao.remove(id);
    }

    @Override
    public void update(Recipe recipe) {
        springJdbcDao.update((RecipeDto) recipeConverter.asDto(recipe));
    }

    @Override
    public void deleteAll() {

    }
}
