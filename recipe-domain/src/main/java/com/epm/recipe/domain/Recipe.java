package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {

    private String title;

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public Recipe() {
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public Recipe(String title, long id) {
        this.title = Objects.requireNonNull(title, "title");
        this.id = id;

    }

    public Recipe(String title) {
        this.title = title;
        //TODO id maybe null?
        id = 0;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }

}
