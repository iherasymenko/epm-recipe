package com.epm.recipe.persistence.db_mysql.exception;

public class DatabasePropertyException extends RuntimeException {
    public DatabasePropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
