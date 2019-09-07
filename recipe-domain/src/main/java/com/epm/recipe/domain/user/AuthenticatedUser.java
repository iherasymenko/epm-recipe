package com.epm.recipe.domain.user;

public class AuthenticatedUser {

    public final User user;
    public final String validFor;
    public final String token;

    public AuthenticatedUser(User user, String validFor, String token) {
        this.user = user;
        this.validFor = validFor;
        this.token = token;
    }

}
