package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDTO;
import com.github.julianomachadoo.pessoasapi.rest.dto.PessoaDetalhadaDTO;
import com.github.julianomachadoo.pessoasapi.rest.form.CriaPessoaForm;
import com.github.julianomachadoo.pessoasapi.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaDetalhadaDTO> criarPessoa(
            @RequestBody @Valid CriaPessoaForm criaPessoaForm, UriComponentsBuilder uriBuilder) {

        PessoaDetalhadaDTO pessoaDetalhadaDTO = service.registraPessoa(criaPessoaForm);
        URI uri =uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaDetalhadaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDetalhadaDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PessoaDetalhadaDTO> editaPessoa(
            @PathVariable Long id, @RequestBody CriaPessoaForm criaPessoaForm) {

        PessoaDetalhadaDTO pessoaDetalhadaDTO = service.editaPessoa(id, criaPessoaForm);
        return ResponseEntity.ok().body(pessoaDetalhadaDTO);

    }
}
