package com.github.julianomachadoo.pessoasapi.rest.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CadastroEnderecoForm {

    @NotNull
    private Long pessoaId;
    @NotBlank
    private String logradouro;
    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String CEP;
    @NotNull
    private Integer numero;
    @NotBlank
    private String cidade;
    @NotNull @JsonProperty
    private boolean isEnderecoPrincipal;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isEnderecoPrincipal() {
        return isEnderecoPrincipal;
    }

    public void setEnderecoPrincipal(boolean enderecoPrincipal) {
        isEnderecoPrincipal = enderecoPrincipal;
    }
}
