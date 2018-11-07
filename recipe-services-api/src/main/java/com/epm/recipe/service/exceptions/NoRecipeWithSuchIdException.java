package com.epm.recipe.service.exceptions;

public class NoRecipeWithSuchIdException extends RuntimeException {

    public NoRecipeWithSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
