package com.desafioTecnico.cadastroPessoas.controller;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioTecnico.cadastroPessoas.dto.PessoaDto;
import com.desafioTecnico.cadastroPessoas.service.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    
    @GetMapping
    public List<PessoaDto> getAllPessoas() {
        return pessoaService.getAllPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> getPessoaById(@PathVariable Long id) {
        PessoaDto pessoa = pessoaService.getPessoaById(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filtro")
    public Page<PessoaDto> getPessoas(@RequestParam("pessoa") String pessoa, Pageable pageable) {
        return pessoaService.getPessoasComFiltro(pessoa, pageable);
    }

    @PostMapping
    public ResponseEntity<PessoaDto> createPessoa(@RequestBody @Valid PessoaDto pessoa) throws InvalidAttributesException {
        PessoaDto createdPessoa = pessoaService.createPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> updatePessoa(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoa) throws InvalidAttributesException {
        PessoaDto updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        if (updatedPessoa != null) {
            return ResponseEntity.ok(updatedPessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        if (pessoaService.deletePessoa(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}