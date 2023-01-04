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
    private String Logradouro;
    private String CEP;
    private Integer numero;
    private String cidade;
    private boolean isEnderecoPrincipal;

    public Endereco() {
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
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
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
        return Objects.equals(id, endereco.id) && Objects.equals(Logradouro, endereco.Logradouro) && Objects.equals(CEP, endereco.CEP) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Logradouro, CEP, numero, cidade);
    }
}
