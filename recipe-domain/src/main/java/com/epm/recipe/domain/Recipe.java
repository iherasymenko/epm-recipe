package com.epm.recipe.domain;

import java.util.Objects;

public class Recipe {

    public long id;
    public String title;

    public Recipe() {
    }

    public Recipe(long id,String title) {
        this.id = id;
        this.title = Objects.requireNonNull(title, "title");
    }


    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Recipe recipe = (Recipe) obj;
        return id == recipe.id
                && (title == recipe.title
                || (title != null &&title.equals(recipe.getTitle())));
    }

    @Override
    public int hashCode() {
        final int prime = 33;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                '}';
    }

}
