package com.epm.recipe.advice;

import com.epm.recipe.domain.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorWrap> handle(ApplicationException exception){
        return new ResponseEntity<>(new ErrorWrap(exception.code(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    public static class ErrorWrap{

        private final String code;
        private final String message;

        public ErrorWrap(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
