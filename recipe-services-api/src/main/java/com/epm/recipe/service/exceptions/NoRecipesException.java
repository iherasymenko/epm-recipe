package com.epm.recipe.service.exceptions;

public class NoRecipesException extends RuntimeException {
    public NoRecipesException(String message, Throwable cause) {
        super(message, cause);
    }
}
