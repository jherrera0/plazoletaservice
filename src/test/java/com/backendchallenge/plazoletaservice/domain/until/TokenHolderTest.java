package com.backendchallenge.plazoletaservice.domain.until;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenHolderTest {

    private MockedStatic<SecurityContextHolder> mockedSecurityContextHolder;
    private SecurityContext securityContext;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class);
        securityContext = mock(SecurityContext.class);
        authentication = mock(Authentication.class);
        mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
    }

    @AfterEach
    void close() {
        mockedSecurityContextHolder.close();
    }

    @Test
    void setToken_shouldReturnTokenWhenAuthenticationIsPresent() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn(ConstTest.VALID_TOKEN);

        String token = TokenHolder.setToken();

        assertNotNull(token);
        assertEquals(ConstJwt.BEARER + ConstJwt.SPLITERSTRING + ConstTest.VALID_TOKEN, token);
    }

    @Test
    void setToken_shouldReturnNullWhenAuthenticationIsNull() {
        when(securityContext.getAuthentication()).thenReturn(null);

        String token = TokenHolder.setToken();

        assertNull(token);
    }

    @Test
    void getToken_shouldReturnTokenWhenAuthenticationIsPresent() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn(ConstTest.VALID_TOKEN);

        String token = TokenHolder.getToken();

        assertNotNull(token);
        assertEquals(ConstJwt.BEARER + ConstJwt.SPLITERSTRING + ConstTest.VALID_TOKEN, token);
    }

    @Test
    void getToken_shouldReturnNullWhenAuthenticationIsNull() {
        when(securityContext.getAuthentication()).thenReturn(null);

        String token = TokenHolder.getToken();

        assertNull(token);
    }

    @Test
    void clear_shouldRemoveToken() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn(ConstTest.VALID_TOKEN);

        TokenHolder.setToken();
        TokenHolder.clear();

        assertNull(TokenHolder.getTokenValue());
    }
}