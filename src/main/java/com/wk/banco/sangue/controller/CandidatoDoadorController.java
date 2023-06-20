package com.wk.banco.sangue.controller;


import com.wk.banco.sangue.domain.model.response.CandidatoDoadorPorEstado;
import com.wk.banco.sangue.domain.model.response.CandidatoImcFaixaEtaria;
import com.wk.banco.sangue.domain.model.response.CandidatosDoadoresPorTipoSanguineo;
import com.wk.banco.sangue.domain.model.response.MediaIdadeTipoSanguineo;
import com.wk.banco.sangue.service.candidato.CandidatoDoadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banco-sangue/candidato-doador")
@AllArgsConstructor
@Api(tags = "Candidatos para doação de sangue")
public class CandidatoDoadorController {


    private final CandidatoDoadorService candidadoDoadorService;


    @GetMapping("/candidatosPorEstado")
    @ApiOperation(value = "Listar candidatos por estado.", notes = "Lista os candidatos a doação de sangue por estado.")
    @ApiModelProperty
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<List<CandidatoDoadorPorEstado>> listCandidatosPorEstado() {
        var listCandidatosPorEstado = candidadoDoadorService.candidatodPorEstado();
        return ResponseEntity.ok(listCandidatosPorEstado);
    }

    @GetMapping("/candidatosImcMedio")
    @ApiOperation(value = "Lista IMC médio dos candidatos.", notes = "Lista IMC médio dos candidatos a doação de sangue.")
    @ApiModelProperty
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<List<CandidatoImcFaixaEtaria>> listCandidatosImcMedio() {
        var listCandidatosImcMedio = candidadoDoadorService.listarCandidatosImcFaixaEtaria();
        return ResponseEntity.ok(listCandidatosImcMedio);
    }

    @GetMapping("/candidatosObesosPorGenero")
    @ApiOperation(value = "Lista percenrtual de candidatos obesos por gênero.", notes = "Lista percenrtual de candidatos obesos por gênero")
    @ApiModelProperty
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<Map<String, Double>> listCandidatosObesosGenero() {
        var listCandidatosObesosPorGenero = candidadoDoadorService.percentualCandidatosObesosEGenero();
        return ResponseEntity.ok(listCandidatosObesosPorGenero);
    }

    @GetMapping("/mediaIdadePorTipoSanguineo")
    @ApiOperation(value = "Lista média de idadde por tipo sanguineo.", notes = "Lista média de idadde dos candidatos por tipo sanguineo.")
    @ApiModelProperty
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<List<MediaIdadeTipoSanguineo>> mediaIdadePorTipoSanguineo() {
        var listCandidatosMediaIdadeTipoSanguineo = candidadoDoadorService.calcularMediaIdadeTipoSanguineo();
        return ResponseEntity.ok(listCandidatosMediaIdadeTipoSanguineo);
    }

    @GetMapping("/possiveisDoadoresPorTipoSanguineo")
    @ApiOperation(value = "Lista média de idadde por tipo sanguineo.", notes = "Lista média de idadde dos candidatos por tipo sanguineo.")
    @ApiModelProperty
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<List<CandidatosDoadoresPorTipoSanguineo>> possiveisDoadoresPorTipoSanguineo() {
        var listFinalDoadores = candidadoDoadorService.listPossiveisDoadoresPorTipoSanguineo();
        return ResponseEntity.ok(listFinalDoadores);
    }

}
