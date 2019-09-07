package com.epm.recipe.domain.user;

import java.time.Duration;
import java.util.Objects;

public class AuthenticationToken {

    public final String token;
    public final Duration validFor;

    public AuthenticationToken(String token, Duration validFor) {
        this.token = Objects.requireNonNull(token);
        this.validFor = validFor;
    }

    public String validForInHours(){
        return validFor.toHours() + " H";
    }
}
