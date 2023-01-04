package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PessoaControllerTest {

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
    void deveriaListarPessoas() throws Exception {
        PessoaBuilder pessoaBuilder = new PessoaBuilder();
        EnderecoBuilder enderecoBuilder = new EnderecoBuilder();

        Endereco endereco = enderecoBuilder
                .comLogradouroCEPNumeroECidade("Av. tal", "49000-000", 1000, "Aracaju")
                .enderecoPrincipal(true).criar();
        Pessoa pessoa = pessoaBuilder
                .comNome("Juliano")
                .comDataDeNascimento(LocalDate.of(1994, 9, 16))
                .comEndereco(endereco).criar();

        Mockito.when(pessoasRepository.findAll()).thenReturn(List.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/pessoas")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}