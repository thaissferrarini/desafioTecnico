package com.desafioTecnico.cadastroPessoas.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafioTecnico.cadastroPessoas.exception.InvalidPessoaException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidPessoaException.class)
    public ResponseEntity<String> handleInvalidCpfException(InvalidPessoaException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}