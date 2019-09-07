package com.epm.recipe.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {

    public final Long userId;
    public final String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public final String password;

    public User(Long userId, String email, String password) {
        this.userId = Objects.requireNonNull(userId);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password);
    }
}
