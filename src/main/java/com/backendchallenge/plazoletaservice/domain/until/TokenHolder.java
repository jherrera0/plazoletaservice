package com.backendchallenge.plazoletaservice.domain.until;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TokenHolder {
    private static final ThreadLocal<String> LOCAL_TOKEN = new ThreadLocal<>();

    private TokenHolder() {
    }

    public static String setToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            LOCAL_TOKEN.set(ConstJwt.BEARER+ConstJwt.SPLITERSTRING +authentication.getCredentials().toString());
            return LOCAL_TOKEN.get();
        }
        return null;

    }

    public static String getToken() {
        return setToken();
    }

    public static String getTokenValue() {
        return LOCAL_TOKEN.get();
    }

    public static void clear() {
        LOCAL_TOKEN.remove();
    }
}