package com.desafioTecnico.cadastroPessoas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafioTecnico.cadastroPessoas.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE (:filtro is null or p.nome like %:filtro%)")
    Page<Pessoa> findByFiltro(
        @Param("filtro") String filtro, 
        Pageable pageable
    );
}
