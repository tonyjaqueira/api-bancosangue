package com.wk.banco.sangue.service.candidato;


import com.wk.banco.sangue.domain.entity.Pessoa;
import com.wk.banco.sangue.domain.enums.GeneroType;
import com.wk.banco.sangue.domain.model.response.CandidatoDoadorPorEstado;
import com.wk.banco.sangue.domain.model.response.CandidatoImcFaixaEtaria;
import com.wk.banco.sangue.domain.model.response.CandidatosDoadoresPorTipoSanguineo;
import com.wk.banco.sangue.domain.model.response.MediaIdadeTipoSanguineo;
import com.wk.banco.sangue.service.pessoa.PessoaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.wk.banco.sangue.domain.enums.GeneroType.FEMININO;
import static com.wk.banco.sangue.domain.enums.GeneroType.MASCULINO;
import static com.wk.banco.sangue.utils.DecimalFormatDate.arrendondarDecimalParaCima;
import static com.wk.banco.sangue.utils.DecimalFormatDate.arrendondarDecimalParaCimaInteiro;

@Service
@AllArgsConstructor
@Slf4j
public class CandidatoDoadorServiceImpl implements CandidatoDoadorService {

    private final PessoaService pessoaService;

    @Override
    public List<CandidatoDoadorPorEstado> candidatodPorEstado() {
        var listFinalCandidatosAgrupados = new ArrayList<CandidatoDoadorPorEstado>();
        var listPessoas = pessoaService.listAllPessoas();
        var resultadoAgrupado = listPessoas.stream().collect(Collectors.groupingBy(pessoa -> pessoa.getEndereco().getCidade().getEstado()));
        resultadoAgrupado.forEach((estado, listPessoasAgrupadas) -> {
            var candidato = CandidatoDoadorPorEstado.builder()
                    .estado(estado)
                    .quantidade(listPessoasAgrupadas.size()).build();
            listFinalCandidatosAgrupados.add(candidato);
        });
        listFinalCandidatosAgrupados.sort(Comparator.comparing(CandidatoDoadorPorEstado::estado));
        return listFinalCandidatosAgrupados;
    }

    @Override
    public List<CandidatoImcFaixaEtaria> listarCandidatosImcFaixaEtaria() {
        var lisPessoas = pessoaService.listAllPessoas();
        var listaCandidatosMediaImc = new ArrayList<CandidatoImcFaixaEtaria>();
        IntStream.range(0, 100)
                .filter(faixaEtaria -> faixaEtaria % 10 == 0) // % operador de modulo, ou resto, se resto da divisão for 0, então esta na faixa etária.
                .forEach(faixaEtaria -> {
                    var faixaEtariaFim = faixaEtaria + 10;
                    var pessoasFaixaEtaria = getPessoasFaixaEtaria(lisPessoas, faixaEtaria, faixaEtariaFim);
                    if ( !pessoasFaixaEtaria.isEmpty() ) {
                        var imcMedio = arrendondarDecimalParaCima(calcularMediaImcGrupoPessoas(pessoasFaixaEtaria));
                        var candidato = CandidatoImcFaixaEtaria.builder()
                                .faixaEtaria(String.valueOf(faixaEtaria).concat(" a ").concat(String.valueOf(faixaEtariaFim)))
                                .imc(imcMedio).build();
                        listaCandidatosMediaImc.add(candidato);
                    }
                });
        return listaCandidatosMediaImc;
    }

    @Override
    public Map<String, Double> percentualCandidatosObesosEGenero() {
        var lisPessoas = pessoaService.listAllPessoas();
        var percentualMasculino = calcularPercentualObesosPorGenero(lisPessoas, MASCULINO);
        var percentualFeminino = calcularPercentualObesosPorGenero(lisPessoas, FEMININO);
        var percentualObesosGenero = new HashMap<String, Double>();
        percentualObesosGenero.put(MASCULINO.getDescription(), percentualMasculino);
        percentualObesosGenero.put(FEMININO.getDescription(), percentualFeminino);
        return percentualObesosGenero;
    }

    @Override
    public List<MediaIdadeTipoSanguineo> calcularMediaIdadeTipoSanguineo() {
        var lisPessoas = pessoaService.listAllPessoas();
        var listMediaIdadePorTipoSanguineo = new ArrayList<MediaIdadeTipoSanguineo>();
        var pessoasPorTipoSanguineo = lisPessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getTipoSanguineo));
        pessoasPorTipoSanguineo.forEach((tipoSanguineo, pessoasDoTipoSanguineo) -> {
            var mediaIdade = arrendondarDecimalParaCimaInteiro(getMediaIdadePorTipoSanguineo(pessoasDoTipoSanguineo));
            var mediaIdadePorTipoSanguineo = MediaIdadeTipoSanguineo.builder()
                    .tipoSanguineo(tipoSanguineo.getDescription())
                    .mediaIdade(mediaIdade).build();
            listMediaIdadePorTipoSanguineo.add(mediaIdadePorTipoSanguineo);
        });
        return listMediaIdadePorTipoSanguineo;
    }

    @Override
    public List<CandidatosDoadoresPorTipoSanguineo> listPossiveisDoadoresPorTipoSanguineo() {
        var lisPessoas = pessoaService.listAllPessoas();
        var possiveisDoadores = new HashMap<String, List<String>>();
        var listFinalDoadores = new ArrayList<CandidatosDoadoresPorTipoSanguineo>();
        lisPessoas.forEach(pessoa -> {
            var idade = calcularIdade(pessoa.getDataNascimento());
            if ( (idade > 15 && idade < 70) && pessoa.getPeso() > 50 ) {
                var tipoReceptor = pessoa.getTipoSanguineo().getDescription();
                var tipoDoador = pessoa.getTipoSanguineo().getDescription();
                verificarCompatibiliadade(tipoReceptor, tipoDoador, possiveisDoadores);
            }
        });
        possiveisDoadores.forEach((tipo, listDoadores) -> {
            var candidatoDoador = CandidatosDoadoresPorTipoSanguineo.builder()
                    .tipoSanguineo(tipo)
                    .quantidade(listDoadores.size()).build();
            listFinalDoadores.add(candidatoDoador);
        });
        return listFinalDoadores;
    }

    private void verificarCompatibiliadade(String tipoReceptor, String tipoDoador, Map<String, List<String>> possiveisDoadores) {
        switch (tipoReceptor) {
            case "A+" -> {
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("A-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O+");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O-");
            }
            case "A-", "B-", "O+" -> {
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O-");
            }
            case "B+" -> {
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("B-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O+");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O-");
            }
            case "AB+" -> {
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("A+");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("B+");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O+");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("A-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("B-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("AB-");
            }
            case "AB-" -> {
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("A-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("B-");
                possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add("O-");
            }
            case "O-" -> possiveisDoadores.computeIfAbsent(tipoReceptor, k -> new ArrayList<>()).add(tipoDoador);
            default -> {
                break;
            }
        }
    }

    private double getMediaIdadePorTipoSanguineo(List<Pessoa> pessoasDoTipoSanguineo) {
        return pessoasDoTipoSanguineo.stream()
                .mapToInt(pessoas -> calcularIdade(pessoas.getDataNascimento()))
                .average()
                .orElse(0);
    }

    private List<Pessoa> getPessoasFaixaEtaria(List<Pessoa> lisPessoas, int faixaEtaria, int faixaEtariaFim) {
        return lisPessoas.stream()
                .filter(pessoa -> calcularIdade(pessoa.getDataNascimento()) >= faixaEtaria && calcularIdade(pessoa.getDataNascimento()) < faixaEtariaFim)
                .toList();
    }

    private static double calcularMediaImcGrupoPessoas(List<Pessoa> pessoasFaixaEtaria) {
        return pessoasFaixaEtaria.stream()
                .mapToDouble(CandidatoDoadorServiceImpl::calculaImc)
                .average()
                .orElse(0);
    }

    private static double calculaImc(Pessoa pessoa) {
        return pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
    }

    private double calcularPercentualObesosPorGenero(List<Pessoa> listPessoas, GeneroType genero) {
        var pessoasGenero = listPessoas.stream()
                .filter(pessoa -> pessoa.getSexo().equals(genero))
                .toList();
        var obesos = pessoasGenero.stream()
                .filter(pessoaImc -> calculaImc(pessoaImc) > 30)
                .count();
        return arrendondarDecimalParaCima((double) obesos / pessoasGenero.size() * 100);
    }

    private int calcularIdade(LocalDate dataNascimento) {
        var periodo = Period.between(dataNascimento, LocalDate.now());
        return periodo.getYears();
    }

}
