package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {

    public final String title;

    public Recipe(String title) {
        this.title = Objects.requireNonNull(title, "title");
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }
}
