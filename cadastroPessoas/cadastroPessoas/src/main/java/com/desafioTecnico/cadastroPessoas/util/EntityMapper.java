package com.desafioTecnico.cadastroPessoas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.desafioTecnico.cadastroPessoas.dto.ContatoDto;
import com.desafioTecnico.cadastroPessoas.dto.PessoaDto;
import com.desafioTecnico.cadastroPessoas.entities.Contato;
import com.desafioTecnico.cadastroPessoas.entities.Pessoa;

public abstract class EntityMapper {
    
    public static Page<PessoaDto> mapPagePessoaToPagePessoaDto(Page<Pessoa> pagePessoa) {
        List<PessoaDto> pessoaDtos = pessoasDto(pagePessoa.getContent());
        return new PageImpl<>(pessoaDtos, pagePessoa.getPageable(), pagePessoa.getTotalElements());
    }

    public static PessoaDto pessoaDto (Pessoa pessoa){

        if(pessoa == null) {
            pessoa = new Pessoa();
        }
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setCpf(pessoa.getCpf());
        pessoaDto.setDataNascimento(pessoa.getDataNascimento());
        pessoaDto.setContatos(contatosDto(pessoa.getContatos()));

        return pessoaDto;
    }

    public static List<PessoaDto> pessoasDto(List<Pessoa> pessoas) {
        List<PessoaDto> list = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            PessoaDto pessoaDto = new PessoaDto();
            pessoaDto = pessoaDto(pessoa);
            list.add(pessoaDto);
        }
        return list;
    }
    
    public static Pessoa pessoa(PessoaDto pessoaDto){

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDto.getId());
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setCpf(pessoaDto.getCpf());
        pessoa.setDataNascimento(pessoaDto.getDataNascimento());
        pessoa.setContatos(contatos(pessoa, pessoaDto.getContatos()));

        return pessoa;
    }

    public static ContatoDto contatoDto(Contato contato) {
        ContatoDto cont = new ContatoDto();
        cont.setId(contato.getId());
        cont.setNome(contato.getNome());
        cont.setEmail(contato.getEmail());
        cont.setTelefone(contato.getTelefone());
        cont.setPessoa(contato.getPessoa());

        return cont;
	}
    
    public static List<ContatoDto> contatosDto(List<Contato> contato) {
        List<ContatoDto> list = new ArrayList<>();
        for (Contato cont : contato) {
            ContatoDto contDto = new ContatoDto();
            contDto = contatoDto(cont);
            list.add(contDto);
        }
        return list;
    }

    
    public static Contato contato(Pessoa pessoa, ContatoDto contato) {
        Contato cont = new Contato();
        cont.setId(contato.getId());
        cont.setNome(contato.getNome());
        cont.setEmail(contato.getEmail());
        cont.setTelefone(contato.getTelefone());
        cont.setPessoa(pessoa);

        return cont;
	}
    
    public static List<Contato> contatos(Pessoa pessoa, List<ContatoDto> contato) {
        List<Contato> list = new ArrayList<>();
        for (ContatoDto cont : contato) {
            Contato contDto = new Contato();
            contDto = contato(pessoa, cont);
            list.add(contDto);
        }
        return list;
    }

}
