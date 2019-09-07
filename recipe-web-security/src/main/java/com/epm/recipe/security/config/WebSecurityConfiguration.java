package com.epm.recipe.security.config;

import com.epm.recipe.security.UserIdentityMethodParameterResolver;
import com.epm.recipe.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserIdentityMethodParameterResolver(authenticationService));
    }
}
