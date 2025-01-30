package com.backendchallenge.plazoletaservice.infrastructure.configuration.security.filter;

import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class JwtService {
    @Value("${app-security-key}")
    private String secretKey = "mysecretkeymysecretkeymysecretkeymy";

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public String extractRole(String jwt) {
        return extractAllClaims(jwt).get(ConstJwt.ROLE).toString();
    }
    public Long extractId(String jwt) {
        return (Long) extractAllClaims(jwt).get(ConstJwt.ID);
    }
    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }

    Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}