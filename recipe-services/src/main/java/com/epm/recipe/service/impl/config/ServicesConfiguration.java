package com.epm.recipe.service.impl.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.UserRepository;
import com.epm.recipe.service.AuthenticationService;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.UserService;
import com.epm.recipe.service.impl.DefaultAuthenticationService;
import com.epm.recipe.service.impl.DefaultRecipeService;

import com.epm.recipe.service.impl.DefaultUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Duration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public RecipeService recipeService(RecipeRepository recipeRepository) {
        return new DefaultRecipeService(recipeRepository);
    }

    @Bean
    public AuthenticationService authenticationService(@Value("${security.secret}") String secret, @Value("${security.expiresAt}") String expiresAt) {
        return new DefaultAuthenticationService(Clock.systemUTC(), secret, Duration.parse(expiresAt));
    }

    @Bean
    public UserService userService(UserRepository userRepository, AuthenticationService authenticationService){
        return new DefaultUserService(userRepository, authenticationService);
    }

}
