package com.wk.banco.sangue.service;


import com.wk.banco.sangue.domain.entity.Cidade;
import com.wk.banco.sangue.domain.entity.Contato;
import com.wk.banco.sangue.domain.entity.Endereco;
import com.wk.banco.sangue.domain.entity.Pessoa;
import com.wk.banco.sangue.domain.enums.GeneroType;
import com.wk.banco.sangue.domain.enums.TipoSanguineoType;
import com.wk.banco.sangue.service.candidato.CandidatoDoadorServiceImpl;
import com.wk.banco.sangue.service.pessoa.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CandidatoServiceTest {

    @InjectMocks
    CandidatoDoadorServiceImpl candidatoDoadorService;

    @Mock
    PessoaServiceImpl pessoaService;

    @BeforeEach
    public void setup() {
        when(pessoaService.listAllPessoas()).thenReturn(listPessoas());
    }

    @Test
    void deveRetornarCandidatosPorEstado() {
        var listCandidatos = candidatoDoadorService.candidatodPorEstado();
        assertFalse(listCandidatos.isEmpty());
        assertEquals(1, listCandidatos.size());
        assertTrue(listCandidatos.stream().anyMatch(candidatoDoadorPorEstado -> candidatoDoadorPorEstado.estado().equals("PE")));
        assertTrue(listCandidatos.stream().anyMatch(candidatoDoadorPorEstado -> candidatoDoadorPorEstado.quantidade().equals(2)));
    }

    @Test
    void deveRetornarImcDeCandidadosPorFaixaEtaria() {
        var listCandidatos = candidatoDoadorService.listarCandidatosImcFaixaEtaria();
        assertFalse(listCandidatos.isEmpty());
        assertEquals(1, listCandidatos.size());
        assertTrue(listCandidatos.stream().anyMatch(candidatoImcFaixaEtaria -> candidatoImcFaixaEtaria.faixaEtaria().equals("30 a 40")));
        assertTrue(listCandidatos.stream().anyMatch(candidatoImcFaixaEtaria -> candidatoImcFaixaEtaria.imc().equals(30.74)));
    }

    @Test
    void deveRetornarCandidatosObesesPorGenero() {
        var mapCandidatos = candidatoDoadorService.percentualCandidatosObesosEGenero();
        assertNotNull(mapCandidatos);
        assertEquals(100.0, mapCandidatos.get("Feminino"));
        assertEquals(0.0, mapCandidatos.get("Masculino"));
    }


    @Test
    void deveCalcularMediaDeIdadePorTipoSanguineo() {
        var listCandidatos = candidatoDoadorService.calcularMediaIdadeTipoSanguineo();
        assertFalse(listCandidatos.isEmpty());
        assertEquals(2, listCandidatos.size());
        assertTrue(listCandidatos.stream().anyMatch(caMediaIdadeTipoSanguineo -> caMediaIdadeTipoSanguineo.mediaIdade().equals(39)));
        assertTrue(listCandidatos.stream().anyMatch(caMediaIdadeTipoSanguineo -> caMediaIdadeTipoSanguineo.tipoSanguineo().equals("B-")));
    }

    @Test
    void deveRetornarPossiveisDoadoresPorTipoSanguineo() {
        var listCandidatos = candidatoDoadorService.listPossiveisDoadoresPorTipoSanguineo();
        assertFalse(listCandidatos.isEmpty());
        assertEquals(2, listCandidatos.size());
        assertTrue(listCandidatos.stream().anyMatch(candidatosDoadoresPorTipoSanguineo -> candidatosDoadoresPorTipoSanguineo.tipoSanguineo().equals("A-")));
        assertTrue(listCandidatos.stream().anyMatch(candidatosDoadoresPorTipoSanguineo -> candidatosDoadoresPorTipoSanguineo.quantidade().equals(2)));
        assertTrue(listCandidatos.stream().anyMatch(candidatosDoadoresPorTipoSanguineo -> candidatosDoadoresPorTipoSanguineo.tipoSanguineo().equals("B-")));
    }


    private List<Pessoa> listPessoas() {
        var pessoa = Pessoa.builder()
                .altura(1.85)
                .mae("Fulana de Tal")
                .cpf("053.035.054-88")
                .pessoaId(1L)
                .pai("Fulano de Tal")
                .rg("5988724")
                .peso(80.2)
                .sexo(GeneroType.MASCULINO)
                .nome("Sicreano de Tal")
                .dataNascimento(LocalDate.of(1984, 3, 20))
                .contato(getContato())
                .endereco(getEndereco())
                .tipoSanguineo(TipoSanguineoType.A_NEGATIVO)
                .build();

        var pessoaFeminino = Pessoa.builder()
                .altura(1.85)
                .mae("Fulana de Tal")
                .cpf("053.035.054-89")
                .pessoaId(1L)
                .pai("Fulano de Tal")
                .rg("5988724")
                .peso(130.2)
                .sexo(GeneroType.FEMININO)
                .nome("Sicreano de Tal")
                .dataNascimento(LocalDate.of(1989, 3, 20))
                .contato(getContato())
                .endereco(getEndereco())
                .tipoSanguineo(TipoSanguineoType.B_NEGATIVO)
                .build();
        return List.of(pessoa, pessoaFeminino);
    }

    private Contato getContato() {
        return Contato.builder()
                .contatoId(1L)
                .email("teste@teste.com")
                .celular("81-999999999")
                .telefoneFixo("81-365656565").build();
    }

    private Endereco getEndereco() {
        return Endereco.builder()
                .enderecoId(1L)
                .bairro("Centro")
                .cep("55555-000")
                .enderecoDescricao("Rua de teste")
                .numero(50)
                .cidade(Cidade.builder().cidadeId(1L).nomeCidade("Recife").estado("PE").build())
                .build();
    }

}
