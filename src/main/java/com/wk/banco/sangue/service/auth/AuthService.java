package com.wk.banco.sangue.service.auth;

import com.wk.banco.sangue.domain.model.request.UsuarioLogin;

public interface AuthService {

    String login(UsuarioLogin login);

}
