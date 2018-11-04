package com.epm.recipe.persistence.in_db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipeDb")
public class RecipeDto {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name="Title")
    private String title;

    public RecipeDto(String title, long id) {
        this.id = id;
        this.title = title;
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
