package com.desafioTecnico.cadastroPessoas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.cadastroPessoas.dto.PessoaDto;
import com.desafioTecnico.cadastroPessoas.entities.Pessoa;
import com.desafioTecnico.cadastroPessoas.repository.PessoaRepository;
import com.desafioTecnico.cadastroPessoas.util.EntityMapper;
import com.desafioTecnico.cadastroPessoas.util.Validacoes;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public List<PessoaDto> getAllPessoas() {
        List<Pessoa> lista = pessoaRepository.findAll();
        return EntityMapper.pessoasDto(lista);
    }

    public PessoaDto getPessoaById(Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        return EntityMapper.pessoaDto(optionalPessoa.get());
    }

    public Page<PessoaDto> getPessoasComFiltro(String nome, Pageable pageable) {
        Page<Pessoa> pessoaPage = pessoaRepository.findByFiltro(nome, pageable);
        
        return EntityMapper.mapPagePessoaToPagePessoaDto(pessoaPage);
    }

    @Transactional
    public PessoaDto createPessoa(PessoaDto pessoa) {
        Validacoes.validacaoPessoa(pessoa);

        Pessoa pessoaSalvar = EntityMapper.pessoa(pessoa);
        
        Pessoa save = pessoaRepository.save(pessoaSalvar);

        return EntityMapper.pessoaDto(save);
    }

    @Transactional
    public PessoaDto updatePessoa(Long id, PessoaDto pessoa) {
        Validacoes.validacaoPessoa(pessoa);
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        if (optionalPessoa.isPresent()) {
            Pessoa existingPessoa = optionalPessoa.get();
            optionalPessoa.get().setNome(pessoa.getNome());
            optionalPessoa.get().setDataNascimento(pessoa.getDataNascimento());
            optionalPessoa.get().setCpf(pessoa.getCpf());
            optionalPessoa.get().getContatos().addAll(EntityMapper.contatos(optionalPessoa.get(), pessoa.getContatos()));

            existingPessoa = pessoaRepository.save(optionalPessoa.get());
            return EntityMapper.pessoaDto(existingPessoa);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deletePessoa(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
