package com.desafioTecnico.cadastroPessoas.util;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.desafioTecnico.cadastroPessoas.dto.ContatoDto;
import com.desafioTecnico.cadastroPessoas.dto.PessoaDto;
import com.desafioTecnico.cadastroPessoas.exception.InvalidPessoaException;

public abstract class Validacoes {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    public static void validacaoPessoa(PessoaDto pessoa) {
        if(pessoa.getNome() == null || pessoa.getNome() == "") {
            throw new InvalidPessoaException("Nome inválido.");
        }
         if (!isCpfValid(pessoa.getCpf())) {
            throw new InvalidPessoaException("CPF inválido.");
        }

        if (pessoa.getDataNascimento().isAfter(LocalDate.now())) {
            throw new InvalidPessoaException("Data de nascimento não pode ser uma data futura.");
        }

        if (pessoa.getContatos() == null || pessoa.getContatos().isEmpty()) {
            throw new InvalidPessoaException("A pessoa deve possuir ao menos um contato.");
        }
        validacaoContatos(pessoa.getContatos());
    }

    public static void validacaoContatos(List<ContatoDto> contatos) {
        for(ContatoDto contato : contatos){
            validacaoContato(contato);
        }

    }
    public static void validacaoContato(ContatoDto contato) {
        if(contato.getNome() == null || contato.getNome() == "") {
            throw new InvalidPessoaException("Nome contato inválido.");
        }
         if (!isEmailValid(contato.getEmail())) {
            throw new InvalidPessoaException("Email inválido.");
        }

         if (!isPhoneValid(contato.getTelefone())) {
            throw new InvalidPessoaException("Telefone inválido.");
        }

    }

    private static boolean isCpfValid(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        return true; 
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isPhoneValid(String phone) {
        if (phone == null || phone.trim().isEmpty() || phone.length() < 8 || phone.length() > 9) {
            return false;
        }
        return true;
    }
}

