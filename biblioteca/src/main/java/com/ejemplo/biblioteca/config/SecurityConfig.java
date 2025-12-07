package com.ejemplo.biblioteca.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private static final String[] SWAGGER_WHITELIST = {
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/webjars/**"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/**").permitAll()
            .requestMatchers(SWAGGER_WHITELIST).permitAll()
            .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
            .requestMatchers("/api/roles/**").hasRole("ADMIN")
            .requestMatchers("/api/libros/**").hasAnyRole("ADMIN", "BIBLIOTECARIO", "CLIENTE")
            .requestMatchers("/api/prestamos/**").hasAnyRole("ADMIN", "BIBLIOTECARIO", "CLIENTE")
            .anyRequest().authenticated())

        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((req, res, authException) -> {
              res.setStatus(401);
              res.setContentType("application/json");
              res.getWriter().write(
                  "{\"error\":\"No autenticado. Debes iniciar sesión o enviar un token válido.\"}");
            })
            .accessDeniedHandler((req, res, accessDeniedException) -> {
              res.setStatus(403);
              res.setContentType("application/json");
              res.getWriter().write(
                  "{\"error\":\"Acceso denegado. No tienes permisos para esta acción.\"}");
            }))

        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }
}