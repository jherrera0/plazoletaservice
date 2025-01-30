package com.backendchallenge.plazoletaservice.infrastructure.configuration.security.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private UserDetailsService myUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void doFilterInternal_noAuthHeader_callsFilterChain() throws ServletException, IOException {
        when(request.getHeader(ConstJwt.HEADER_STRING)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }


    @Test
    void doFilterInternal_authHeaderWithoutBearer_callsFilterChain() throws ServletException, IOException {
        when(request.getHeader(ConstJwt.HEADER_STRING)).thenReturn("InvalidHeader");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_validAuthHeader_authenticatesUser () throws ServletException, IOException {
        String validJwt = ConstTest.VALID_TOKEN;
        when(request.getHeader(ConstJwt.HEADER_STRING)).thenReturn(ConstJwt.BEARER + ConstJwt.SPLITERSTRING+ validJwt);
        when(myUserDetailsService.loadUserByUsername(validJwt)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(List.of());

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(myUserDetailsService).loadUserByUsername(validJwt);
        verify(filterChain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_malformedJwt_returnsBadRequest () throws ServletException, IOException {
        String malformedJwt = ConstTest.INVALID_TOKEN;
        when(request.getHeader(ConstJwt.HEADER_STRING)).thenReturn(ConstJwt.BEARER + ConstJwt.SPLITERSTRING+ malformedJwt);
        doThrow(new IllegalArgumentException(ConstExceptions.MALFORMED_JWT)).when(myUserDetailsService).loadUserByUsername(malformedJwt);
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertEquals(ConstExceptions.MALFORMED_JWT, stringWriter.toString().trim());
        verify(filterChain, never()).doFilter(request, response);
    }
}