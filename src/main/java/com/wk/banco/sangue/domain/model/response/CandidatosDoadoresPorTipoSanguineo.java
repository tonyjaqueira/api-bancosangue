package com.wk.banco.sangue.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CandidatosDoadoresPorTipoSanguineo(

        @Schema(description = "Tipo Sanguineo do possível doador", example = "AB+")
        String tipoSanguineo,

        @Schema(description = "Quantidade por Tipo Sanguineo", example = "8")
        Integer quantidade

) {}
