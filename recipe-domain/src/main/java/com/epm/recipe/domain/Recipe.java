package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {
    private String title;
    private long id;

    public Recipe() {}

    public Recipe(String title) {
        id = 0;
        this.title = title;
    }

    public Recipe(long id, String title) {
        this.title = Objects.requireNonNull(title, "title");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(title, recipe.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }

}
