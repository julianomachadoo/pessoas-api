package com.github.julianomachadoo.pessoasapi.service;

import com.github.julianomachadoo.pessoasapi.exceptions.DadosNaoEncontradosException;
import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.rest.dto.EnderecoDTO;
import com.github.julianomachadoo.pessoasapi.rest.form.CadastroEnderecoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoasRepository pessoasRepository;

    public EnderecoDTO cadastrarEndereco(CadastroEnderecoForm cadastroEnderecoForm) {
        Optional<Pessoa> pessoa = pessoasRepository.findById(cadastroEnderecoForm.getPessoaId());
        if (pessoa.isEmpty()) throw new DadosNaoEncontradosException("Pessoa não encontrada");

        if (cadastroEnderecoForm.isEnderecoPrincipal()) {
            List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoa.get().getId());
            enderecos.forEach(endereco -> endereco.setEnderecoPrincipal(false));
        }

        Endereco endereco = new Endereco(
                cadastroEnderecoForm.getLogradouro(),
                cadastroEnderecoForm.getCEP(),
                cadastroEnderecoForm.getNumero(),
                cadastroEnderecoForm.getCidade(),
                cadastroEnderecoForm.isEnderecoPrincipal(),
                pessoa.get());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return criaEnderecoDTO(enderecoSalvo);
    }

    public EnderecoDTO getEnderecoPrincipal(Long id) {
        Optional<Pessoa> pessoa = pessoasRepository.findById(id);
        if (pessoa.isEmpty()) throw new DadosNaoEncontradosException("Pessoa não encontrada");
        try {
            Endereco endereco = pessoa.get().getEnderecos().stream()
                    .filter(Endereco::isEnderecoPrincipal).findFirst().get();
            return criaEnderecoDTO(endereco);

        } catch (NoSuchElementException e) {
            throw new DadosNaoEncontradosException("Nao foi encontrado endereço principal para a pessoa informada");
        }
    }

    private EnderecoDTO criaEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setPessoaNome(endereco.getPessoa().getNome());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setCEP(endereco.getCEP());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEnderecoPrincipal(endereco.isEnderecoPrincipal());
        return enderecoDTO;
    }
}
