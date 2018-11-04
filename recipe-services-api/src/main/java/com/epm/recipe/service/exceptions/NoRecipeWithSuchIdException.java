package com.epm.recipe.service.exceptions;

public class NoRecipeWithSuchIdException extends RuntimeException {

    public NoRecipeWithSuchIdException(Throwable cause) {
        super(cause);
    }
}
