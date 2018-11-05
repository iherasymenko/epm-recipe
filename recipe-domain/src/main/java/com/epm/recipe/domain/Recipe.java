package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {

    private String title;

    private long id;

    public Recipe() { }

    public Recipe(String title) {
        this.title = title;
        id = 0;
    }

    public Recipe(String title, long id) {
        this.title = Objects.requireNonNull(title, "title");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) { this.title = title; }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }

}
