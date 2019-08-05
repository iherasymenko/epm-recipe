package com.epm.recipe.persistence.jdbc.exception;

public class JdbcPersistenceException extends RuntimeException {

    public JdbcPersistenceException(String message){
        super(message);
    }
}
