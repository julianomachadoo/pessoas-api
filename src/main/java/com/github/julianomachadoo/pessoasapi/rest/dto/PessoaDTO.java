package com.github.julianomachadoo.pessoasapi.rest.dto;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;

import java.time.LocalDate;
import java.util.Optional;

public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private Endereco enderecoPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }
}
