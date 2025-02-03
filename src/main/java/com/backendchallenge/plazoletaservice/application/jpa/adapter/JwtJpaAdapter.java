package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.application.jwt.JwtService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtJpaAdapter implements IJwtPersistencePort {
    private final JwtService jwtService;
    @Override
    public Long getUserId(String token) {
        return jwtService.extractId(token);
    }
}
