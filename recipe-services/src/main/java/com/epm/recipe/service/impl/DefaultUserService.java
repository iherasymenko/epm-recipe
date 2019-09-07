package com.epm.recipe.service.impl;

import com.epm.recipe.domain.user.*;
import com.epm.recipe.persistence.UserRepository;
import com.epm.recipe.service.AuthenticationService;
import com.epm.recipe.service.UserService;

import java.util.Optional;

public class DefaultUserService implements UserService {

    private final UserRepository repository;
    private final AuthenticationService authenticationService;

    public DefaultUserService(UserRepository repository, AuthenticationService service) {
        this.repository = repository;
        this.authenticationService = service;
    }

    @Override
    public AuthenticatedUser login(UserLoginRequest request) throws UserNotFoundException, InvalidPasswordException {
        User user = repository.getByEmail(request.email)
                .orElseThrow(UserNotFoundException::new);
        if (user.password.equals(request.password)){
            AuthenticationToken token = authenticationService.generateToken(user.userId);
            return new AuthenticatedUser(user, token.validForInHours(), token.token);
        } else {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public AuthenticatedUser register(UserCreateRequest request) throws UserAlreadyExistException {
        Optional<User> user = repository.getByEmail(request.email);
        if (user.isEmpty()){
            User createdUser = repository.create(request.email, request.password);
            AuthenticationToken token = authenticationService.generateToken(createdUser.userId);
            return new AuthenticatedUser(createdUser, token.validForInHours(), token.token);
        } else {
            throw new UserAlreadyExistException(request.email + " already exist");
        }
    }
}
