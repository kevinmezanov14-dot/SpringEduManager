package com.edu.manager.config;

import com.edu.manager.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación con Spring Security.
 * <p>
 * Define la protección de rutas, el manejo de login y logout, la configuración de roles
 * y la integración del filtro JWT para APIs REST.
 * </p>
 * 
 * Funcionalidades clave:
 * <ul>
 *     <li>Definición de rutas públicas y protegidas según roles (USER, ADMIN).</li>
 *     <li>Configuración de login con formulario personalizado y logout.</li>
 *     <li>Integración de {@link JwtFilter} para autenticación vía tokens JWT en endpoints /api/**.</li>
 *     <li>Definición de manejo de acceso denegado (403).</li>
 *     <li>Exposición de {@link PasswordEncoder} y {@link AuthenticationManager} como beans para la aplicación.</li>
 * </ul>
 * 
 * Autor: Kevin
 */
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Bean de codificación de contraseñas.
     * 
     * @return PasswordEncoder que utiliza BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean de AuthenticationManager utilizado por Spring Security.
     *
     * @param config configuración de autenticación
     * @return AuthenticationManager para manejar la autenticación
     * @throws Exception si falla la configuración
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http objeto HttpSecurity para personalizar la seguridad
     * @return SecurityFilterChain configurado
     * @throws Exception si falla la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Deshabilita CSRF para las rutas API
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
            
            // Configuración de acceso a rutas
            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos públicos
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                
                // Login y registro
                .requestMatchers("/login", "/registro").permitAll()
                
                // Endpoints públicos de API
                .requestMatchers("/api/auth/**").permitAll()
                
                // Endpoints de API protegidos
                .requestMatchers("/api/**").authenticated()
                
                // Home protegido
                .requestMatchers("/").authenticated()
                
                // Rutas sensibles solo ADMIN
                .requestMatchers(
                    "/evaluaciones/nuevo", "/evaluaciones/editar/**", "/evaluaciones/eliminar/**",
                    "/cursos/nuevo", "/cursos/editar/**", "/cursos/eliminar/**",
                    "/practicas/nuevo", "/practicas/editar/**", "/practicas/eliminar/**",
                    "/estudiantes/nuevo", "/estudiantes/editar/**", "/estudiantes/eliminar/**"
                ).hasRole("ADMIN")
                
                // Rutas generales accesibles por USER y ADMIN
                .requestMatchers("/evaluaciones/**", "/cursos/**", "/practicas/**")
                    .hasAnyRole("USER", "ADMIN")
                
                // Paneles específicos por rol
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            
            // Configuración de login con formulario personalizado
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            
            // Configuración de logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )
            
            // Agrega filtro JWT antes del filtro de autenticación de Spring
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            
            // Manejo de acceso denegado (403)
            .exceptionHandling(ex -> ex.accessDeniedPage("/error/403"));

        return http.build();
    }
}