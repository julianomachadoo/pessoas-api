package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.json.JSONException;
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

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {

    private static final String PESSOAS = "/pessoas";
    private static final String PESSOAS_1 = "/pessoas/1";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PessoasRepository pessoasRepository;
    @MockBean
    EnderecoRepository enderecoRepository;

    String JsonCadastro = new JSONObject()
            .put("nome", "Fulano")
            .put("dataDeNascimento", "1993-12-05")
            .toString();

    PessoaControllerTest() throws JSONException {
    }

    @BeforeEach
    public void iniciar() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveriaListarPessoas() throws Exception {
        Endereco endereco = EnderecoBuilder.criaEnderecoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(pessoa)));

        mockMvc.perform(MockMvcRequestBuilders
                .get(PESSOAS)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void naoDeveriaDetalharPessoaNaoCadastrada() throws Exception {
        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                .get(PESSOAS_1)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveriaDetalharUmaPessoaCadastrada() throws Exception {
        Endereco endereco = EnderecoBuilder.criaEnderecoNaoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.get(PESSOAS_1)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void naoDeveriaCriarUmaPessoaComDadosInvalidos() throws Exception {
        Pessoa pessoa = PessoaBuilder.criaPessoaExemploSemEndereco();

        Mockito.when(pessoasRepository.save(ArgumentMatchers.any())).thenReturn(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(new JSONObject()
                                .put("dataDeNascimento", "1993-12-05")
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(new JSONObject()
                                .put("nome", "")
                                .put("dataDeNascimento", "1993-12-05")
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(new JSONObject()
                                .put("nome", "Juliano")
                                .put("dataDeNascimento", "199-12-05")
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(new JSONObject()
                                .put("nome", "Juliano")
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(new JSONObject()
                                .put("nome", "Juliano")
                                .put("dataDeNascimento", "")
                                .toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deveriaCriarUmaPessoa() throws Exception {
        Pessoa pessoa = PessoaBuilder.criaPessoaExemploSemEndereco();

        Mockito.when(pessoasRepository.save(ArgumentMatchers.any())).thenReturn(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.post(PESSOAS)
                        .content(JsonCadastro)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deveriaEditarUmaPessoa() throws Exception {
        Pessoa pessoa = PessoaBuilder.criaPessoaExemploSemEndereco();

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.put(PESSOAS_1)
                        .content(JsonCadastro).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}