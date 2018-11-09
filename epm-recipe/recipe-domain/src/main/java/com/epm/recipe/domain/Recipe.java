package com.epm.recipe.domain;

import java.io.Serializable;
import java.util.Objects;

public class Recipe {

    public String title;
    public long id;

    public Recipe() {
    }

    public Recipe(String title, long id) {
        this.title = Objects.requireNonNull(title, "title");
        this.id = id;

    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
