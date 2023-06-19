package com.wk.banco.sangue.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CandidatoDoadorPorEstado(

        @Schema(description = "Quantidade de candidatos", example = "10")
        Integer quantidade,

        @Schema(description = "Estado da Federação dos candidatos", example = "PE")
        String estado

) {}
