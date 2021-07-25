package com.example.aluraflix.config;

import com.example.aluraflix.resource.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Error> handle(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        return fieldErrors.stream().map(fieldError -> {
            Error error = new Error();
            error.setField(fieldError.getField());

            final var mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            error.setMessage(mensagem);


            return error;
        }).collect(Collectors.toList());
    }

}
