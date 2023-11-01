package com.desafioTecnico.cadastroPessoas.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    private String cpf;
    @Past
    private LocalDate dataNascimento;
    @NotBlank
    private List<ContatoDto> contatos;
    
}
