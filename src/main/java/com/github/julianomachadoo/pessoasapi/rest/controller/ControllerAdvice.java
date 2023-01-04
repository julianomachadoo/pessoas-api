package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.exceptions.DadosNaoEncontradosException;
import com.github.julianomachadoo.pessoasapi.rest.dto.ApiErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DadosNaoEncontradosException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorsDTO handleDadosNaoEncontrados(DadosNaoEncontradosException ex) {
        return new ApiErrorsDTO(ex.getMessage());
    }

}
