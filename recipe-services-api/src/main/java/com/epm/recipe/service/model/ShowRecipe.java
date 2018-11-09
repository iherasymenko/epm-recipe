package com.epm.recipe.service.model;

import java.util.Objects;

public class ShowRecipe {
    private String title;
    private long id;

    public ShowRecipe(String title, long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowRecipe that = (ShowRecipe) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}
