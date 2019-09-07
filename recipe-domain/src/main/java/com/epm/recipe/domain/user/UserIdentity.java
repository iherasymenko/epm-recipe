package com.epm.recipe.domain.user;

import java.util.Objects;

public class UserIdentity {

    public final long userId;

    public UserIdentity(Long userId) {
        this.userId = Objects.requireNonNull(userId);
    }

}
