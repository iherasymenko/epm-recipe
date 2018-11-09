package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {

    public final String title;
    public final long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
                Objects.equals(title, recipe.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }

}
