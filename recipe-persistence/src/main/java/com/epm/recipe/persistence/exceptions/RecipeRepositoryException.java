package com.epm.recipe.persistence.exceptions;

public class RecipeRepositoryException extends RuntimeException {
    public RecipeRepositoryException(String message) {
        super(message);
    }
}
