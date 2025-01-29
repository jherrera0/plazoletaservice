package com.backendchallenge.plazoletaservice.infrastructure.configuration.feign;

import com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler.FeignExceptionHandler;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignExceptionHandler();
    }
}
