package com.backendchallenge.plazoletaservice.domain.until;

public class ConstJwt {
    public static final String BEARER = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE = "Role";
    public static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    public static final int LINESTRING_INDEX = 7;
    public static final String PERMIT_ALL = "permitAll()";
    public static final String HAS_AUTHORITY_OWNER = "hasAuthority('OWNER')";
    public static final String SPLITERSTRING = " ";
    public static final String ID = "Id";
    public static final String HAS_AUTHORITY_CLIENT = "hasAuthority('CLIENT')";
    public static final String HAS_ROLE_ADMIN = "hasAuthority('EMPLOYEE')";
    public static final String HAS_AUTHORITY_EMPLOYEE = "hasAuthority('EMPLOYEE')";

    private ConstJwt() {
    }
}
