package com.epm.recipe.web_ui.controller;

class Api {
    static final String ROOT = "api";

    class Recipes {
        static final String RECIPES = "/recipes";
        static final String RECIPE = "/recipes/{id}";
        static final String RECIPE_OF_THE_DAY = "/recipes/recipe_of_the_day";
    }

    class RecipesUi {
        static final String REDIRECT = "redirect:/api";
        static final String RECIPE_OF_THE_DAY_UI = "/recipes_ui/recipe_of_the_day";
        static final String ALL_RECIPES_UI = "/recipes_ui/all_recipes";
        static final String ADD_RECIPE_UI = "/recipes_ui/add_recipe";
        static final String GET_RECIPE_UI = "/recipes_ui/get_recipe";
        static final String UPDATE_RECIPE_UI = "/recipes_ui/update_recipe";
        static final String DELETE_RECIPE_UI = "/recipes_ui/delete_recipe";
    }
}
