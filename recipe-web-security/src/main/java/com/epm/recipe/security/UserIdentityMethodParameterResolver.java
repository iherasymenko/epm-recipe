package com.epm.recipe.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.epm.recipe.domain.user.AuthenticationToken;
import com.epm.recipe.domain.user.UserIdentity;
import com.epm.recipe.service.AuthenticationService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

public class UserIdentityMethodParameterResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationService authenticationService;

    public UserIdentityMethodParameterResolver(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UserIdentity.class == parameter.getParameterType();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory){
        String userKey = webRequest.getHeader("user-key");
        if (userKey == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access token is mandatory");
        }
        try {
            return authenticationService.resolveUserId(new AuthenticationToken(userKey, null));
        } catch (JWTVerificationException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }
    }
}
