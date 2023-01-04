package com.github.julianomachadoo.pessoasapi.rest.dto;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDetalhadaDTO {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private final List<Endereco> enderecos = new ArrayList<>();

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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }
}
