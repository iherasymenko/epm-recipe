package com.epm.recipe.advice.config;

import com.epm.recipe.advice.ExceptionControllerAdvice;
import com.epm.recipe.advice.OptionalResponseControllerAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAdviceConfiguration {

    @Bean
    public ExceptionControllerAdvice exceptionControllerAdvice(){
        return new ExceptionControllerAdvice();
    }

    @Bean
    public OptionalResponseControllerAdvice optionalResponseControllerAdvice(){
        return new OptionalResponseControllerAdvice();
    }

}
