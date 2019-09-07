package com.epm.recipe.web_api.controller;

import com.epm.recipe.domain.user.*;
import com.epm.recipe.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AuthenticatedUser login(UserLoginRequest request) throws UserNotFoundException, InvalidPasswordException {
        return userService.login(request);
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AuthenticatedUser register(UserCreateRequest request) throws UserAlreadyExistException {
        return userService.register(request);
    }
}
