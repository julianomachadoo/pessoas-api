package com.github.julianomachadoo.pessoasapi.service;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoasRepository pessoasRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoasRepository.findAll();
        return pessoas.stream().map(this::criarPessoaDTO).collect(Collectors.toList());
    }

    private PessoaDTO criarPessoaDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoaDTO.setEnderecoPrincipal(
                pessoa.getEnderecos().stream().filter(Endereco::isEnderecoPrincipal).findFirst().get());
        return pessoaDTO;
    }
}
