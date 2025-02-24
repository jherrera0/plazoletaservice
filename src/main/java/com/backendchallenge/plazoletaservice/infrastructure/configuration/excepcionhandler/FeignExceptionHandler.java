package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.backendchallenge.plazoletaservice.domain.until.ConstExceptions.*;

public class FeignExceptionHandler implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String s, Response response) {
        String errorMessage = getErrorMessage(response);
        return switch (response.status()) {
            case CODE_400-> throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
            case CODE_404->throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
            case CODE_302-> throw new ResponseStatusException(HttpStatus.FOUND, errorMessage);
            default -> defaultErrorDecoder.decode(s, response);
        };
    }

    String getErrorMessage(Response response) {
        try {
            if (response.body() != null) {
                return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            return getErrorMessage(response);
        }
        return Optional.ofNullable(response.reason()).orElse(getErrorMessage(response));
    }
}