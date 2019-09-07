package com.epm.recipe.domain.user;

import com.epm.recipe.domain.ApplicationException;

public class UserAlreadyExistException extends ApplicationException {

    private final static String exceptionCode = "user-1";

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public String code() {
        return exceptionCode;
    }
}
