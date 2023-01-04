package com.github.julianomachadoo.pessoasapi.repository;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByPessoaId(Long id);
}
