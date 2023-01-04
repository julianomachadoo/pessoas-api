package com.github.julianomachadoo.pessoasapi.rest.dto;

public class ApiErrorsDTO {
    private final String error;

    public ApiErrorsDTO(String mensagemErro) {
        this.error = mensagemErro;
    }

    public String getError() {
        return error;
    }
}
