package com.github.julianomachadoo.pessoasapi.util.builder;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaBuilder {

    private String nome;
    private LocalDate dataDeNascimento;
    private final List<Endereco> enderecos = new ArrayList<>();

    public PessoaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaBuilder comDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public PessoaBuilder comEndereco(Endereco endereco) {
        enderecos.add(endereco);
        return this;
    }

    public Pessoa criar() {
        Pessoa pessoa = new Pessoa(nome, dataDeNascimento);
        enderecos.forEach(endereco -> endereco.setPessoa(pessoa));
        enderecos.forEach(pessoa::adicionarEndereco);
        return pessoa;
    }

    public static Pessoa criaPessoaExemplo(List<Endereco> enderecos) {
        PessoaBuilder pessoaBuilder = new PessoaBuilder()
                .comNome("Juliano")
                .comDataDeNascimento(LocalDate.of(1994, 9, 16));
        enderecos.forEach(pessoaBuilder::comEndereco);
        return pessoaBuilder.criar();
    }

    public static Pessoa criaPessoaExemploSemEndereco() {
        return new PessoaBuilder()
                .comNome("Juliano")
                .comDataDeNascimento(LocalDate.of(1994, 9, 16))
                .criar();
    }
}
