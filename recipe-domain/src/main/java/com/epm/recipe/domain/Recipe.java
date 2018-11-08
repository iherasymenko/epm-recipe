package com.epm.recipe.domain;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {

    public long id;
    public String title;

    public Recipe() {
    }

    public Recipe(long id, String title) {
        this.id = id;
        this.title = Objects.requireNonNull(title, "title");
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
