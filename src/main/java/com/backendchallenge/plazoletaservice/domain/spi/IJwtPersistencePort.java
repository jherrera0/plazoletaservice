package com.backendchallenge.plazoletaservice.domain.spi;

public interface IJwtPersistencePort {
    Long getUserId(String token);
}
