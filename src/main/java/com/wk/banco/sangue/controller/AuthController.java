package com.wk.banco.sangue.controller;


import com.wk.banco.sangue.domain.model.request.UsuarioLogin;
import com.wk.banco.sangue.domain.model.response.JWTAuthResponse;
import com.wk.banco.sangue.service.auth.AuthService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco-sangue/auth")
@AllArgsConstructor
@Api(tags = "Login para Autenticação")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody UsuarioLogin usuarioLogin){
        var token = authService.login(usuarioLogin);
        return ResponseEntity.ok(JWTAuthResponse.builder().accessToken(token).build());
    }

}
