package com.desafioTecnico.cadastroPessoas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.desafioTecnico.cadastroPessoas.dto.ContatoDto;
import com.desafioTecnico.cadastroPessoas.dto.PessoaDto;
import com.desafioTecnico.cadastroPessoas.entities.Contato;
import com.desafioTecnico.cadastroPessoas.entities.Pessoa;
import com.desafioTecnico.cadastroPessoas.repository.PessoaRepository;
import com.desafioTecnico.cadastroPessoas.util.Validacoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PessoaServiceTest {

    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void init(){
        this.pessoaService = new PessoaService(pessoaRepository);
        
        this.mockGetPessoaId1();

        this.mockSavePessoaId2();
    }

    private void mockSavePessoaId2() {
        Pessoa pessoa = Pessoa.builder()
            .id(2L)
            .nome("Maria")
            .cpf("12345678900")
            .dataNascimento(LocalDate.now())
            .build();
        List<Contato> contatos = new ArrayList<>();

        Contato contato = Contato.builder()
            .email("teste2@gmail.com")
            .id(2L)
            .nome("primo do get")
            .telefone("27 993123403")
            .pessoa(pessoa)
            .build();
        
        contatos.add(contato);

        pessoa.setContatos(contatos);

        when(this.pessoaRepository.save(any())).thenReturn(pessoa);
    }

    private void mockGetPessoaId1() {
        Pessoa pessoa = Pessoa.builder()
            .id(1L)
            .nome("null")
            .cpf("1111111105")
            .dataNascimento(LocalDate.now())
            .build();
        List<Contato> contatos = new ArrayList<>();

        Contato contato = Contato.builder()
            .email("teste1@gmail.com")
            .id(1L)
            .nome("primo do null")
            .telefone("27 993123404")
            .pessoa(pessoa)
            .build();
        
        contatos.add(contato);

        pessoa.setContatos(contatos);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
    }

    @Test
    public void testGetPessoaById() {
        
        Long pessoaId = 1L;
        PessoaDto pessoa = PessoaDto.builder()
        .id(1L)
        .nome("null")
        .cpf("1111111105")
        .build();

        PessoaDto result = pessoaService.getPessoaById(pessoaId);

        assertEquals(pessoa.getCpf(), result.getCpf());
        assertEquals(pessoa.getId(), result.getId());
        assertEquals(pessoa.getNome(), result.getNome());
    }

    @Test
    public void testCreatePessoa() {
        PessoaDto pessoa = new PessoaDto();
        pessoa.setNome("Maria");
        pessoa.setCpf("12345678900");
        pessoa.setDataNascimento(LocalDate.of(1992, 3, 22));
        pessoa.setId(2L);

        ContatoDto contato = new ContatoDto();
        contato.setNome("Contato 1");
        contato.setTelefone("123456789");
        contato.setEmail("contato1@example.com");

        List<ContatoDto> contatos = new ArrayList<>();
        contatos.add(contato);
        pessoa.setContatos(contatos);

        PessoaDto createdPessoa = pessoaService.createPessoa(pessoa);

        assertEquals(pessoa.getCpf(), createdPessoa.getCpf());
        assertEquals(pessoa.getId(), createdPessoa.getId());
        assertEquals(pessoa.getNome(), createdPessoa.getNome());
    }

    @Test
    public void testUpdatePessoa() {
        // Criar um objeto PessoaDto para representar a pessoa atualizada
        PessoaDto pessoaAtualizada = new PessoaDto();
        pessoaAtualizada.setId(1L);
        pessoaAtualizada.setNome("Novo Nome");
        pessoaAtualizada.setCpf("12345678901");
        pessoaAtualizada.setDataNascimento(LocalDate.of(1990, 1, 1));

        ContatoDto contato = new ContatoDto();
        contato.setNome("Contato 1");
        contato.setTelefone("123456789");
        contato.setEmail("contato1@example.com");

        List<ContatoDto> contatos = new ArrayList<>();
        contatos.add(contato);
        pessoaAtualizada.setContatos(contatos);
        // Simular o retorno de findById com a pessoa existente
        Pessoa pessoaExistente = Pessoa.builder()
            .id(1L)
            .nome("Nome Antigo")
            .cpf("12345678900")
            .dataNascimento(LocalDate.of(1980, 1, 1))
            .build();

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaExistente));

        // Simular o retorno de save após a atualização
        Pessoa pessoaAtualizadaEntity = Pessoa.builder()
            .id(1L)
            .nome("Novo Nome")
            .cpf("12345678901")
            .dataNascimento(LocalDate.of(1990, 1, 1))
            .build();

        Contato contato2 = new Contato();
        contato.setNome("Contato 1");
        contato.setTelefone("123456789");
        contato.setEmail("contato1@example.com");

        List<Contato> contatos2 = new ArrayList<>();
        contatos2.add(contato2);
        pessoaAtualizadaEntity.setContatos(contatos2);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaAtualizadaEntity);

        // Chamar o método de atualização
        PessoaDto resultado = pessoaService.updatePessoa(1L, pessoaAtualizada);

        // Verificar se o resultado corresponde aos valores esperados
        assertEquals(pessoaAtualizada.getId(), resultado.getId());
        assertEquals(pessoaAtualizada.getNome(), resultado.getNome());
        assertEquals(pessoaAtualizada.getCpf(), resultado.getCpf());
        assertEquals(pessoaAtualizada.getDataNascimento(), resultado.getDataNascimento());
    }

    @Test
    public void testDeletePessoaExistente() {
        // Simule que o ID existe no repositório
        Long id = 1L;
        when(pessoaRepository.existsById(id)).thenReturn(true);

        // Execute o método de exclusão
        boolean resultado = pessoaService.deletePessoa(id);

        // Verifique se o método de exclusão retornou true
        assertTrue(resultado);

        // Verifique se o método de exclusão foi chamado com o ID correto
        verify(pessoaRepository).deleteById(id);
    }

    @Test
    public void testDeletePessoaInexistente() {
        // Simule que o ID não existe no repositório
        Long id = 2L;
        when(pessoaRepository.existsById(id)).thenReturn(false);

        // Execute o método de exclusão
        boolean resultado = pessoaService.deletePessoa(id);

        // Verifique se o método de exclusão retornou false
        assertFalse(resultado);

        // Verifique se o método de exclusão não foi chamado
        verify(pessoaRepository, never()).deleteById(id);
    }
}