package com.backendchallenge.plazoletaservice.infrastructure.configuration.feign;

import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler.FeignExceptionHandler;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfig {
    @Bean
    public static RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header(ConstJwt.HEADER_STRING, token);
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignExceptionHandler();
    }
}
