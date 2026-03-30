package com.edu.manager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Filtro JWT que intercepta solicitudes HTTP dirigidas a los endpoints de
 * la API y valida el token JWT en la cabecera Authorization.
 *
 * Se ejecuta una sola vez por solicitud y se asegura de:
 *   - Extraer el token JWT de la cabecera Authorization.
 *   - Cargar el UserDetails para validar el token contra el usuario real.
 *   - Autenticar al usuario en el contexto de seguridad de Spring.
 *   - Ignorar solicitudes que no inicien con "/api/".
 *
 * Autor: Kevin
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor de JwtFilter.
     *
     * @param jwtUtil            utilidad para operaciones con JWT
     * @param userDetailsService servicio para cargar información del usuario
     */
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Indica si este filtro NO debe aplicarse a la solicitud actual.
     * Solo se filtran rutas que comienzan con "/api/".
     *
     * @param request solicitud HTTP
     * @return true si no se debe filtrar, false en caso contrario
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().startsWith("/api/");
    }

    /**
     * Lógica principal del filtro.
     * Extrae el token, carga el usuario y valida el token contra ese usuario.
     *
     * @param request     solicitud HTTP
     * @param response    respuesta HTTP
     * @param filterChain cadena de filtros
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extraerToken(request);

            if (token != null) {
                String username = jwtUtil.extraerUsername(token);  

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtUtil.esTokenValido(token, userDetails)) { 
                        autenticarUsuario(userDetails, request);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error en filtro JWT: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT de la cabecera Authorization.
     *
     * @param request solicitud HTTP
     * @return el token JWT sin el prefijo "Bearer ", o null si no existe
     */
    private String extraerToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    /**
     * Autentica al usuario en el contexto de seguridad de Spring.
     *
     * @param userDetails detalles del usuario ya cargado y validado
     * @param request     solicitud HTTP
     */
    private void autenticarUsuario(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        logger.info("Usuario autenticado via JWT: {}", userDetails.getUsername());
    }
}