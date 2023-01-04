package com.github.julianomachadoo.pessoasapi.repository;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.util.builder.EnderecoBuilder;
import com.github.julianomachadoo.pessoasapi.util.builder.PessoaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    void deveriaCarregarEnderecosPorPessoaId() {
        Endereco endereco = EnderecoBuilder.criaEnderecoNaoPrincipalExemplo();
        Pessoa pessoa = PessoaBuilder.criaPessoaExemplo(Collections.singletonList(endereco));
        Pessoa pessoaPersist = em.persist(pessoa);
        Endereco enderecoPersist = em.persist(endereco);

        List<Endereco> enderecos = repository.findByPessoaId(pessoaPersist.getId());
        assertNotNull(enderecos);
        assertEquals(1, enderecos.size());
        assertEquals(enderecoPersist, enderecos.get(0));
        assertEquals(pessoaPersist, enderecos.get(0).getPessoa());
    }
}