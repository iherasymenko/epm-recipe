package com.epm.recipe.domain.user;

import java.util.Objects;

public class UserCreateRequest {

    public final String email;
    public final String password;

    public UserCreateRequest(String email, String password) {
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }
}
