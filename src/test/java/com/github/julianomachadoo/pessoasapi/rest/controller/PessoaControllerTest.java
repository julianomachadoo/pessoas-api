package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Endereco endereco = criaEnderecoExemplo();
        Pessoa pessoa = criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findAll()).thenReturn(List.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/pessoas")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void naoDeveriaDetalharPessoaNaoCadastrada() throws Exception {
        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/pessoas/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveriaDetalharUmaPessoaCadastrada() throws Exception {
        Endereco endereco = criaEnderecoExemplo();
        Pessoa pessoa = criaPessoaExemplo(Collections.singletonList(endereco));

        Mockito.when(pessoasRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private Endereco criaEnderecoExemplo() {
        return new EnderecoBuilder()
                .comLogradouroCEPNumeroECidade("Av. tal", "49000-000", 1000, "Aracaju")
                .enderecoPrincipal(true).criar();
    }

    private Pessoa criaPessoaExemplo(List<Endereco> enderecos) {
        PessoaBuilder pessoaBuilder = new PessoaBuilder()
                .comNome("Juliano")
                .comDataDeNascimento(LocalDate.of(1994, 9, 16));
        enderecos.forEach(pessoaBuilder::comEndereco);
        return pessoaBuilder.criar();
    }
}