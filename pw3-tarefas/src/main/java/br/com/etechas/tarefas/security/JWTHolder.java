package br.com.etechas.tarefas.security;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.enums.RoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JWTHolder {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Duration expiration;

    private Key signingKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("id", usuario.getId())
                .claim("role", usuario.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration.toMillis()))
                .signWith(signingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Converte token de volta para Usuario (apenas dados básicos)
    public Usuario getAuthenticated(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Usuario usuario = new Usuario();
        usuario.setId(claims.get("id", Long.class));
        usuario.setUsername(claims.getSubject());
        usuario.setRole(RoleEnum.valueOf(claims.get("role", String.class)));
        return usuario;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                               authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
