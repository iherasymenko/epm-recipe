package com.epm.recipe.domain.user;

import com.epm.recipe.domain.ApplicationException;

public class InvalidPasswordException extends ApplicationException {

    private final static String exceptionCode = "user-3";

    @Override
    public String code() {
        return exceptionCode;
    }
}
