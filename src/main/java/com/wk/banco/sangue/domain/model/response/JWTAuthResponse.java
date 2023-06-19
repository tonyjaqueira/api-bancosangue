package com.wk.banco.sangue.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record JWTAuthResponse(

        @Schema(description = "Token de resposta", example = "a56s4da65sda5s4d5a4sd4asd4a6s4d6a54sd6a4s6d4as65d4")
        String accessToken

) {}
