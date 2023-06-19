package com.wk.banco.sangue.service.candidato;

import com.wk.banco.sangue.domain.model.response.CandidatoDoadorPorEstado;
import com.wk.banco.sangue.domain.model.response.CandidatoImcFaixaEtaria;
import com.wk.banco.sangue.domain.model.response.CandidatosDoadoresPorTipoSanguineo;
import com.wk.banco.sangue.domain.model.response.MediaIdadeTipoSanguineo;

import java.util.List;
import java.util.Map;

public interface CandidadoDoadorService {

    List<CandidatoDoadorPorEstado> candidatodPorEstado();

    List<CandidatoImcFaixaEtaria> listarCandidatosImcFaixaEtaria();

    Map<String, Double> percentualCandidatosObesosEGenero();

    List<MediaIdadeTipoSanguineo> calcularMediaIdadeTipoSanguineo();

    List<CandidatosDoadoresPorTipoSanguineo> listPossiveisDoadoresPorTipoSanguineo();
}
