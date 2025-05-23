package com.example.auth2fa.config;

import org.springframework.context.annotation.Configuration;
import com.example.auth2fa.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Bean de type SecurityFilterChain qui configure les règles de sécurité pour l'application.
     * Cette configuration spécifie comment traiter les requêtes entrantes.
     * @param http L'objet HttpSecurity permettant de configurer la sécurité.
     * @return La chaîne de filtres de sécurité configurée.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Désactivation de la protection CSRF (Cross-Site Request Forgery), car nous utilisons des JWT.
                .csrf(csrf -> csrf.disable())
                // 2. Configuration de la gestion de session pour indiquer qu'elle est "sans état" (stateless).
                // Cela signifie que Spring Security ne stockera pas les informations d'utilisateur dans une session HTTP.
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 3. Autorisations pour les requêtes HTTP.
                .authorizeHttpRequests(auth -> auth
                        // Autoriser toutes les requêtes commençant par `/auth/**` sans authentification.
                        .requestMatchers("/auth/**").permitAll()
                        // Toute autre requête doit être authentifiée (protection par défaut).
                        .anyRequest().authenticated()
                )
                // 4. Ajout du filtre personnalisé `JwtAuthenticationFilter` avant le filtre standard `UsernamePasswordAuthenticationFilter`.
                // Cela permet à notre filtre d'intercepter et valider les JWT avant toute logique standard d'authentification.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // 5. Construction et retour de la configuration de la chaîne de filtres.
                .build();
    }
}
