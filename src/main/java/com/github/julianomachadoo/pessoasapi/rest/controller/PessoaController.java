package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDTO;
import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDetalhadaDTO;
import com.github.julianomachadoo.pessoasapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaDTO> listarPessoas() {
        return service.listarPessoas();
    }

    @GetMapping("/{id}")
    public PessoaDetalhadaDTO consultarPessoa(@PathVariable Long id) {
        return service.consultarPessoa(id);
    }
}
