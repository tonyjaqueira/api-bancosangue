package com.wk.banco.sangue.domain.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioLogin(

        @Schema(description = "Login de usuário", example = "fulano")
        String username,

        @Schema(description = "Senha do usuário", example = "abc123456")
        String password

) {}
