package com.backendchallenge.plazoletaservice.infrastructure.configuration.security.filter;

import com.backendchallenge.plazoletaservice.application.jwt.JwtService;
import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;
    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_validJwt_returnsUserDetails() {
        String jwt = ConstTest.VALID_TOKEN;
        String username = ConstTest.USER_VALID;
        String role = ConstTest.ROLE_NAME_TEST;

        when(jwtService.extractUsername(jwt)).thenReturn(username);
        when(jwtService.extractRole(jwt)).thenReturn(role);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwt);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals(role, userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void loadUserByUsername_invalidJwt_throwsUsernameNotFoundException() {
        String jwt = ConstTest.INVALID_TOKEN;

        when(jwtService.extractUsername(jwt)).thenThrow(new UsernameNotFoundException(ConstExceptions.INVALID_TOKEN));

        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_nullJwt_throwsUsernameNotFoundException() {
        String jwt = null;

        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_emptyJwt_throwsUsernameNotFoundException() {
        String jwt = ConstTest.EMPTY_TEST;

        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }
}