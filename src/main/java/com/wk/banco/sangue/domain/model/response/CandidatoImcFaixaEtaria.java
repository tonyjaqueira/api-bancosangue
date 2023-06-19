package com.wk.banco.sangue.domain.model.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CandidatoImcFaixaEtaria(

        @Schema(description = "Faixa etária do Candidato a doador", example = "0 a 10")
        String faixaEtaria,

        @Schema(description = "IMC do Candidato a doador", example = "20")
        Double imc

) {}
