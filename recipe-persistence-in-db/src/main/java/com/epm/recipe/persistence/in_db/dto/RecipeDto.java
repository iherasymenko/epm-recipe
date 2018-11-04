package com.epm.recipe.persistence.in_db.dto;


public class RecipeDto {

    private long id;

    private String title;

    public RecipeDto(String title, long id) {
        this.id = id;
        this.title = title;
    }

    public RecipeDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
