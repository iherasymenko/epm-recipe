package com.epm.recipe.service.dto;

import java.util.Objects;

public class UpdateRecipeDto {
    private String title;
    private long id;

    public UpdateRecipeDto() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRecipeDto that = (UpdateRecipeDto) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}
