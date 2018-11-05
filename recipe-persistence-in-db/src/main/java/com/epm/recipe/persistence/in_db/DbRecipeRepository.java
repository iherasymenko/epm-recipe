package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_db.converter.Converter;
import com.epm.recipe.persistence.in_db.dao.RecipeDao;
import com.epm.recipe.persistence.in_db.dao.SpringJdbcDao;
import com.epm.recipe.persistence.in_db.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DbRecipeRepository implements RecipeRepository {
    private List<Recipe> recipeList;

    @Autowired
    SpringJdbcDao recipeDao;

    @Autowired
    Converter converter;

    public SpringJdbcDao getRecipeDao() {
        return recipeDao;
    }

    public void setRecipeDao(SpringJdbcDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public DbRecipeRepository() {
//        recipeDao  = new SpringJdbcDao();
    }

    public DbRecipeRepository(SpringJdbcDao recipeDao, Converter converter) {
        this.recipeDao = recipeDao;
        this.converter = converter;
    }

    @Override
    public List<Recipe> findAll() {
        return converter.asObj(recipeDao.findAll());
    }

    @Override
    public void add(Recipe recipe) {
        recipeDao.add((RecipeDto) converter.asDto(recipe));
    }

    @Override
    public Recipe getById(long id) {
        Recipe recipe = (Recipe) converter.asObj(recipeDao.getById(id));
        return recipe;
    }

    @Override
    public void deleteById(long id) {
//        Recipe recipe = getById(id);
        recipeDao.remove(id);
    }

    @Override
    public void update(Recipe recipe) {
        recipeDao.update((RecipeDto) converter.asDto(recipe));
    }

    @Override
    public void deleteAll() {
        recipeDao.deleteAll();
    }
}
