package com.wk.banco.sangue.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record MediaIdadeTipoSanguineo(

        @Schema(description = "Média de idade dos candidatos", example = "25")
        Integer mediaIdade,

        @Schema(description = "Tipo Sanguineo", example = "A+")
        String tipoSanguineo

) {}
