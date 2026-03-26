package com.edu.manager.config;

import com.edu.manager.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth

	            // 🔹 recursos estáticos
	            .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

	            // 🔹 login y registro
	            .requestMatchers("/login", "/registro").permitAll()

	            // 🔹 API pública
	            .requestMatchers("/api/auth/login", "/api/auth/registro").permitAll()

	            // 🔹 API protegida
	            .requestMatchers("/api/auth/me").authenticated()
	            .requestMatchers("/api/users/**").hasRole("ADMIN")

	            // 🔹 HOME protegido
	            .requestMatchers("/").authenticated()

	            .anyRequest().authenticated()
	        )

	        // 🔥 ESTO SOLUCIONA TU 403
	        .formLogin(form -> form
	            .loginPage("/login")
	            .permitAll()
	        )

	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}