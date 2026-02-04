package com.genflow.genflowai.security;


public final class SecurityConstants {

    private SecurityConstants() {
        // prevent instantiation
    }

    // ===== JWT =====
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // ===== CLAIMS =====
    public static final String ROLE_CLAIM = "role";

    // ===== AUTH PATHS =====
    public static final String AUTH_BASE_PATH = "/api/auth/**";

}
