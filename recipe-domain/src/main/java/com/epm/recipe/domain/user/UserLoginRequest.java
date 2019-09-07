package com.epm.recipe.domain.user;

import java.util.Objects;

public class UserLoginRequest {

    public final String email;
    public final String password;

    public UserLoginRequest(String email, String password) {
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }
}
