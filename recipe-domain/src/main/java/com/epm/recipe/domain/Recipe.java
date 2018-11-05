package com.epm.recipe.domain;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {

    private static final long serialVersionUID = 670152912384142452L;

    private Long id;

    private String title;

    public Recipe() {
    }

    public Recipe(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be NULL!");
    }

    public Recipe(Long id, String title) {
        this.id = Objects.requireNonNull(id, "ID cannot be NULL!");
        this.title = Objects.requireNonNull(title, "Title cannot be NULL!");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Objects.requireNonNull(id, "ID cannot be NULL!");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be NULL!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recipe)) {
            return false;
        }

        Recipe recipe = (Recipe) o;

        if (!getId().equals(recipe.getId())) {
            return false;
        }
        return getTitle().equals(recipe.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
