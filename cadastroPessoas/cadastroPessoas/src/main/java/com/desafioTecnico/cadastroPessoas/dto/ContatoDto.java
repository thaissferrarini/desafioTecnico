package com.desafioTecnico.cadastroPessoas.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.desafioTecnico.cadastroPessoas.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ContatoDto {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    @JsonIgnore
    private Pessoa pessoa;

}
