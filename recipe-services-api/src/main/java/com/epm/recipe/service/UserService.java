package com.epm.recipe.service;

import com.epm.recipe.domain.user.*;

public interface UserService {

    AuthenticatedUser login(UserLoginRequest request) throws UserNotFoundException, InvalidPasswordException;

    AuthenticatedUser register(UserCreateRequest request) throws UserAlreadyExistException;
}
