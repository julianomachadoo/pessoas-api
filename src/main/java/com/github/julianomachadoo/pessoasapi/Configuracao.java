package com.github.julianomachadoo.pessoasapi;

import com.github.julianomachadoo.pessoasapi.modelo.Endereco;
import com.github.julianomachadoo.pessoasapi.modelo.Pessoa;
import com.github.julianomachadoo.pessoasapi.repository.EnderecoRepository;
import com.github.julianomachadoo.pessoasapi.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("default")
public class Configuracao implements CommandLineRunner {

	@Autowired
	PessoasRepository pessoasRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

    @Override
    public void run(String... args) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Juliano");
		pessoa.setDataDeNascimento(LocalDate.of(1994, 9, 16));

		Endereco endereco = new Endereco();
		endereco.setCEP("49000-000");
		endereco.setCidade("Aracaju");
		endereco.setLogradouro("Alguma Avenida");
		endereco.setNumero(3000);
		endereco.setPessoa(pessoa);
		endereco.setEnderecoPrincipal(true);

		Endereco endereco2 = new Endereco();
		endereco2.setCEP("49000-100");
		endereco2.setCidade("Aracaju");
		endereco2.setLogradouro("Alguma outra avenida");
		endereco2.setNumero(3001);
		endereco2.setPessoa(pessoa);
		endereco2.setEnderecoPrincipal(false);

		pessoa.adicionarEndereco(endereco);
		pessoa.adicionarEndereco(endereco2);

		pessoasRepository.save(pessoa);
		enderecoRepository.save(endereco);
		enderecoRepository.save(endereco2);
    }
}
