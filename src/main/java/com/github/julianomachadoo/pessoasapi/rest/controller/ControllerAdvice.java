package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.exceptions.DadosNaoEncontradosException;
import com.github.julianomachadoo.pessoasapi.rest.dto.ApiErrorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvice {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(DadosNaoEncontradosException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorsDTO handleDadosNaoEncontrados(DadosNaoEncontradosException ex) {
        return new ApiErrorsDTO(ex.getMessage());
    }

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiErrorsDTO> handle(MethodArgumentNotValidException exception) {
        List<ApiErrorsDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ApiErrorsDTO erro = new ApiErrorsDTO(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }

}
