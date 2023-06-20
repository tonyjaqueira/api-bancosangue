package com.wk.banco.sangue.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { //garante que o filtro seja aplicado apenas uma vez por solicitação.

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    @Value("${jwt.urlServidor}")
    private String originPermitida;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = getTokenFromRequest(request);

        //validar token
        if ( StringUtils.hasText(token) && jwtTokenProvider.validateToken(token) ) {
            var username = jwtTokenProvider.getUsername(token);
            var userDetails = userDetailsService.loadUserByUsername(username);
            var authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        String[] allowDomain = originPermitida.split(",");
        List<String> allowedOrigins = new ArrayList<>(Arrays.asList(allowDomain));

        if ( allowedOrigins.contains(request.getHeader("Origin")) || isNull(request.getHeader("Origin")) ) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin")); //origem que será permitida a requisição
            response.setHeader("Access-Control-Allow-Credentials", "true"); //para o cookie do refresh_token
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        if ( request.getMethod().equals("OPTIONS") ) {
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,PATCH,OPTIONS"); //setando os HEADRES para os metodos http que iremos trabalhar
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, X-Auth-Token"); //headers que iremos permitir
            response.setHeader("Access-Control-Allow-Max-Age", "3600"); //tempo em que o browser vai fazer a proxima requisição = 1 hora
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        if ( StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ") ) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
