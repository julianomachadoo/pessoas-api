package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class EnderecoControllerTest {

    private static final String ENDERECOS = "/enderecos";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PessoasRepository pessoasRepository;
    @MockBean
    EnderecoRepository enderecoRepository;

    @BeforeEach
    public void iniciar() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveriaCadastrarEnderecoInvalido() throws Exception {
        Endereco endereco = EnderecoBuilder.criaEnderecoNaoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));
        Mockito.when(enderecoRepository.findByPessoaId(ArgumentMatchers.anyLong())).thenReturn(List.of(endereco));
        Mockito.when(enderecoRepository.save(ArgumentMatchers.any())).thenReturn(endereco);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("logradouro", "Av exemplo")
                                .put("cep", "55555-333")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("cep", "55555-333")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("cep", "5555-333")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("cep", "55555-333")
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("cep", "55555-333")
                                .put("numero", 4444)
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void naoDeveriaCadastrarComPessoaNaoRegistrada() throws Exception{
        Endereco endereco = EnderecoBuilder.criaEnderecoNaoPrincipalExemplo();

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(enderecoRepository.findByPessoaId(ArgumentMatchers.anyLong())).thenReturn(List.of(endereco));
        Mockito.when(enderecoRepository.save(ArgumentMatchers.any())).thenReturn(endereco);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("cep", "55555-333")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveriaCadastrarEndereco() throws Exception {
        Endereco endereco = EnderecoBuilder.criaEnderecoNaoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));
        Mockito.when(enderecoRepository.findByPessoaId(ArgumentMatchers.anyLong())).thenReturn(List.of(endereco));
        Mockito.when(enderecoRepository.save(ArgumentMatchers.any())).thenReturn(endereco);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDERECOS)
                        .content(new JSONObject()
                                .put("pessoaId", "1")
                                .put("logradouro", "Av exemplo")
                                .put("cep", "55555-333")
                                .put("numero", 4444)
                                .put("cidade", "Cidade de mentira")
                                .put("isEnderecoPrincipal", true)
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deveriaRetornarErroAoBuscarEnderecoPrincipalNaoCadastrado() throws Exception {
        Pessoa pessoa = PessoaBuilder.criaPessoaExemploSemEndereco();

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveriaRetornarEnderecoPrincipal() throws Exception {
        Endereco endereco = EnderecoBuilder.criaEnderecoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}