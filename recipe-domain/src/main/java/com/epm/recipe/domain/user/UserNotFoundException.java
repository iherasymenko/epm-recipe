package com.epm.recipe.domain.user;

import com.epm.recipe.domain.ApplicationException;

public class UserNotFoundException extends ApplicationException {

    private final static String exceptionCode = "user-2";

    @Override
    public String code() {
        return exceptionCode;
    }
}
