package com.github.julianomachadoo.pessoasapi.service;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.rest.form.CadastroEnderecoForm;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class EnderecoServiceTest {

    @MockBean
    PessoasRepository pessoasRepository;
    @MockBean
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoService enderecoService;

    @BeforeEach
    public void iniciar() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void aoCadastrarUmEnderecoPrincipalTodosOsOutrosDevemVirarAuxiliares() {
        Endereco endereco = EnderecoBuilder.criaEnderecoPrincipalExemplo();
        endereco.setCidade("Outra cidade");
        endereco.setId(1L);
        Endereco enderecoPrincipal = EnderecoBuilder.criaEnderecoPrincipalExemplo();
        enderecoPrincipal.setId(2L);
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(List.of(endereco));
        pessoa.setId(1L);
        endereco.setPessoa(pessoa);
        enderecoPrincipal.setPessoa(pessoa);

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));
        Mockito.when(enderecoRepository.findByPessoaId(ArgumentMatchers.anyLong())).thenReturn(List.of(endereco));
        Mockito.when(enderecoRepository.save(ArgumentMatchers.any())).thenReturn(enderecoPrincipal);

        CadastroEnderecoForm enderecoForm = converter(enderecoPrincipal, pessoa);

        enderecoService.cadastrarEndereco(enderecoForm);
        pessoa.adicionarEndereco(enderecoPrincipal);

        assertEquals(2, pessoa.getEnderecos().size());
        assertEquals(enderecoPrincipal, pessoa.getEnderecos().stream()
                .filter(Endereco::isEnderecoPrincipal).findFirst().get());
    }

    private CadastroEnderecoForm converter (Endereco endereco, Pessoa pessoa) {
        CadastroEnderecoForm cadastroEnderecoForm = new CadastroEnderecoForm();
        cadastroEnderecoForm.setPessoaId(pessoa.getId());
        cadastroEnderecoForm.setLogradouro(endereco.getLogradouro());
        cadastroEnderecoForm.setCEP(endereco.getCEP());
        cadastroEnderecoForm.setNumero(endereco.getNumero());
        cadastroEnderecoForm.setCidade(endereco.getCidade());
        cadastroEnderecoForm.setEnderecoPrincipal(endereco.isEnderecoPrincipal());
        return cadastroEnderecoForm;
    }

}