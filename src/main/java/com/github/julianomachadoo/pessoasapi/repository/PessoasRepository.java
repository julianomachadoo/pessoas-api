package com.github.julianomachadoo.pessoasapi.repository;

import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoa, Long> {
}
