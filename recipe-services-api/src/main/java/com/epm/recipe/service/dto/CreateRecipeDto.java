package com.epm.recipe.service.dto;

import java.util.Objects;

public class CreateRecipeDto {
    private String title;

    public CreateRecipeDto() {
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
        CreateRecipeDto recipeDto = (CreateRecipeDto) o;
        return Objects.equals(title, recipeDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
