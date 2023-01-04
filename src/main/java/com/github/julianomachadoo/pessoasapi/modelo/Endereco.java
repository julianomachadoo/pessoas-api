package com.github.julianomachadoo.pessoasapi.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Pessoa pessoa;
    private String logradouro;
    private String CEP;
    private Integer numero;
    private String cidade;
    private boolean isEnderecoPrincipal;

    public Endereco() {
    }

    public Endereco(String logradouro, String cep, Integer numero, String cidade, boolean isEnderecoPrincipal) {
        this.logradouro = logradouro;
        this.CEP = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.isEnderecoPrincipal = isEnderecoPrincipal;
    }

    public Endereco(String logradouro, String cep, Integer numero, String cidade, boolean isEnderecoPrincipal, Pessoa pessoa) {
        this.logradouro = logradouro;
        this.CEP = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.isEnderecoPrincipal = isEnderecoPrincipal;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(CEP, endereco.CEP) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logradouro, CEP, numero, cidade);
    }
}
