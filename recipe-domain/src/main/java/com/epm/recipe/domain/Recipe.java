package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {
    public final int id;
    public final String title;
    public final int price;

    public Recipe(int id, String title, int price) {
        this.id = id;
        this.title = Objects.requireNonNull(title, "title");
        this.price = price;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                "price='" + price + '\'' +
                '}';
    }

}
