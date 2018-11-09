package com.epm.recipe.service.model;

import java.util.Objects;

public class CreateRecipe {
    private String title;

    public CreateRecipe() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateRecipe recipeDto = (CreateRecipe) o;
        return Objects.equals(title, recipeDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}