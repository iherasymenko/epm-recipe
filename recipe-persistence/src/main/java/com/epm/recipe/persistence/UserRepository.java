package com.epm.recipe.persistence;

import com.epm.recipe.domain.user.User;
import com.epm.recipe.domain.user.UserNotFoundException;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getByEmail(String email);

    User create(String email, String password);

}
