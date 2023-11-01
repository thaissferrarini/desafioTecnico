package com.desafioTecnico.cadastroPessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafioTecnico.cadastroPessoas.entities.Contato;
import com.desafioTecnico.cadastroPessoas.entities.Pessoa;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("SELECT p FROM Contato p WHERE p.pessoa = :pessoa")
    List<Contato> findByPessoa( @Param("pessoa") Pessoa pessoa);

}
