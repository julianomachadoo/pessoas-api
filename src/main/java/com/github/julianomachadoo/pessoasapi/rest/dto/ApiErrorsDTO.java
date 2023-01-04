package com.github.julianomachadoo.pessoasapi.rest.dto;

public class ApiErrorsDTO {

    private String campo;

    private final String error;

    public ApiErrorsDTO(String mensagemErro) {
        this.error = mensagemErro;
    }

    public ApiErrorsDTO(String campo, String error) {
        this.campo = campo;
        this.error = error;
    }

    public String getCampo() {
        return campo;
    }

    public String getError() {
        return error;
    }
}
