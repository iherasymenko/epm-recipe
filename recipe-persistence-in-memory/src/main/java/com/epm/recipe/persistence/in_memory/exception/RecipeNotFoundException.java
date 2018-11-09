package com.epm.recipe.persistence.in_memory.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
