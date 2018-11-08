package com.epm.recipe.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 4005673311849288102L;

    private String title;
    public final Long id;

    @JsonCreator
    public Recipe(@JsonProperty(value = "title", required = true) String title,
                  @JsonProperty(value = "id") Long id) {
        this.title = Objects.requireNonNull(title, "title");
        this.id = id;

    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
