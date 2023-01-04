package com.github.julianomachadoo.pessoasapi.rest.controller;

import com.github.julianomachadoo.pessoasapi.rest.dto.EnderecoDTO;
import com.github.julianomachadoo.pessoasapi.rest.form.CadastroEnderecoForm;
import com.github.julianomachadoo.pessoasapi.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<EnderecoDTO> cadastraEnderecoParaPessoa(
            @RequestBody @Valid CadastroEnderecoForm cadastroEnderecoForm, UriComponentsBuilder uriBuilder) {
        EnderecoDTO enderecoDTO = service.cadastrarEndereco(cadastroEnderecoForm);

        URI uri = uriBuilder.path("/enderecos/{id}").buildAndExpand(enderecoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoDTO);
    }

    @GetMapping("/{id}")
    public EnderecoDTO getEnderecoPrincipal(@PathVariable Long id) {
        return service.getEnderecoPrincipal(id);
    }
}
