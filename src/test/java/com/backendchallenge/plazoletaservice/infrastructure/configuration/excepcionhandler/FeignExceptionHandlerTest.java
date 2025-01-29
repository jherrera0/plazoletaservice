package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FeignExceptionHandlerTest {
    private Response.Body responseBody;
    @InjectMocks
    private FeignExceptionHandler feignExceptionHandler;

    @Mock
    private Response response;

    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        response = mock(Response.class);
        responseBody = mock(Response.Body.class);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void decode_withStatus400_shouldReturnBadRequestException() throws IOException {
        when(response.status()).thenReturn(ConstExceptions.CODE_400);
        when(response.body()).thenReturn(mock(Response.Body.class));
        when(response.body().asInputStream()).thenReturn(IOUtils.toInputStream(HttpStatus.BAD_REQUEST.getReasonPhrase(), StandardCharsets.UTF_8));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            feignExceptionHandler.decode("methodKey", response);
        });

        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatusCode().value());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getReason());
    }

    @Test
    void decode_withStatus404_shouldReturnNotFoundException() throws IOException {
        when(response.status()).thenReturn(ConstExceptions.CODE_404);
        when(response.body()).thenReturn(mock(Response.Body.class));
        when(response.body().asInputStream()).thenReturn(IOUtils.toInputStream(HttpStatus.NOT_FOUND.getReasonPhrase(), StandardCharsets.UTF_8));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            feignExceptionHandler.decode("methodKey", response);
        });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getReason());
    }

    @Test
    void decode_withStatus302_shouldReturnInternalServerErrorException() throws IOException {
        when(response.status()).thenReturn(ConstExceptions.CODE_302);
        when(response.body()).thenReturn(mock(Response.Body.class));
        when(response.body().asInputStream()).thenReturn(IOUtils.toInputStream(HttpStatus.FOUND.getReasonPhrase(), StandardCharsets.UTF_8));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            feignExceptionHandler.decode("methodKey", response);
        });

        assertEquals(HttpStatus.FOUND.value(),  exception.getStatusCode().value());
        assertEquals(HttpStatus.FOUND.getReasonPhrase(),exception.getReason());
    }
    @Test
    void getErrorMessage_shouldReturnBodyContent_whenBodyIsNotNull() throws Exception {
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Error Message".getBytes(StandardCharsets.UTF_8)));

        String errorMessage = feignExceptionHandler.getErrorMessage(response);

        assertEquals("Error Message", errorMessage);
    }
}