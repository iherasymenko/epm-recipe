package com.epm.recipe.domain;

public abstract class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public abstract String code();

}
