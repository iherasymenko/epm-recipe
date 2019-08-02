package com.epm.recipe.persistence.db_mysql.exception;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
