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

}
