package com.github.julianomachadoo.pessoasapi.util.builder;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;

public class EnderecoBuilder {

    private String logradouro;
    private String CEP;
    private Integer numero;
    private String cidade;
    private boolean isEnderecoPrincipal;

    public EnderecoBuilder comLogradouroCEPNumeroECidade(String logradouro, String CEP, Integer numero, String cidade) {
        this.logradouro = logradouro;
        this.CEP = CEP;
        this.numero = numero;
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilder enderecoPrincipal(boolean isEnderecoPrincipal) {
        this.isEnderecoPrincipal = isEnderecoPrincipal;
        return this;
    }

    public Endereco criar(){
    return new Endereco(logradouro, CEP, numero, cidade, isEnderecoPrincipal);
    }

    public static Endereco criaEnderecoNaoPrincipalExemplo() {
        return new EnderecoBuilder()
                .comLogradouroCEPNumeroECidade("Av. tal", "49000-000", 1000, "Aracaju")
                .enderecoPrincipal(false).criar();
    }

    public static Endereco criaEnderecoPrincipalExemplo() {
        return new EnderecoBuilder()
                .comLogradouroCEPNumeroECidade("Av. tal", "49000-000", 1000, "Aracaju")
                .enderecoPrincipal(true).criar();
    }
}
