package com.gerenciador.tarefas.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciador.tarefas.entity.UsuarioAutenticado;
import com.gerenciador.tarefas.service.AutenticacaoService;

import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFiltro extends AbstractAuthenticationProcessingFilter {

    public LoginFiltro(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            UsuarioAutenticado usuarioAutenticado = new ObjectMapper().readValue(collect, UsuarioAutenticado.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuarioAutenticado.getUsername(),
                            usuarioAutenticado.getPassword(),
                            Collections.emptyList()));
        } catch (IOException e) {
            logger.error("Erro ao processar dados de autenticação", e);
            throw new AuthenticationServiceException("Erro ao processar dados de autenticação", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain,
            Authentication authentication) throws IOException, ServletException {

        AutenticacaoService.addJWTToken(httpServletResponse, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        logger.error("Falha na autenticação", failed);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
