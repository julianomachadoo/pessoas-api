package com.github.julianomachadoo.pessoasapi.service;

import com.github.julianomachadoo.pessoasapi.exceptions.DadosNaoEncontradosException;
import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDTO;
import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDetalhadaDTO;
import com.github.julianomachadoo.pessoasapi.rest.form.CriaPessoaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoasRepository pessoasRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoasRepository.findAll();
        return pessoas.stream().map(this::criaPessoaDTO).collect(Collectors.toList());
    }

    public PessoaDetalhadaDTO consultarPessoa(Long id) {
        Optional<Pessoa> pessoa = pessoasRepository.findById(id);
        if (pessoa.isEmpty()) throw new DadosNaoEncontradosException("Pessoa não encontrada");
        return criaPessoaDetalhadaDTO(pessoa.get());
    }

    public PessoaDetalhadaDTO registraPessoa(CriaPessoaForm criaPessoaForm) {
        Pessoa pessoa = pessoasRepository.save(new Pessoa(criaPessoaForm.getNome(), criaPessoaForm.getDataDeNascimento()));
        return criaPessoaDetalhadaDTO(pessoa);
    }

    private PessoaDTO criaPessoaDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoaDTO.setEnderecoPrincipal(
                pessoa.getEnderecos().stream().filter(Endereco::isEnderecoPrincipal).findFirst().get());
        return pessoaDTO;
    }

    private PessoaDetalhadaDTO criaPessoaDetalhadaDTO(Pessoa pessoa) {
        PessoaDetalhadaDTO pessoaDetalhadaDTO = new PessoaDetalhadaDTO();
        pessoaDetalhadaDTO.setId(pessoa.getId());
        pessoaDetalhadaDTO.setNome(pessoa.getNome());
        pessoaDetalhadaDTO.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoa.getEnderecos().forEach(pessoaDetalhadaDTO::adicionarEndereco);
        return pessoaDetalhadaDTO;
    }

    private void verificaEnderecoPrincipal(Pessoa pessoa) {
        int contador = 0;
        for (Endereco endereco : pessoa.getEnderecos()) {
            if (endereco.isEnderecoPrincipal()) contador++;
        }
        if (contador > 1)
            throw new IllegalArgumentException("Não pode haver mais de um endereço principal por pessoa");
    }
}
