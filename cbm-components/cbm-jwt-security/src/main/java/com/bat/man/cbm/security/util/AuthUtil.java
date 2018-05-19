package com.bat.man.cbm.security.util;

import com.bat.man.cbm.security.domain.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public abstract class AuthUtil {

    public static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void setAuth(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static String getJwt() {
        Authentication auth = getAuth();
        if (auth != null && auth.getCredentials() instanceof String) {
            return (String) auth.getCredentials();
        } else {
            return null;
        }
    }

    public static AuthUser getAuthUser() {
        Authentication auth = getAuth();
        if (auth != null && auth.getPrincipal() instanceof AuthUser) {
            return (AuthUser) auth.getPrincipal();
        } else {
            return null;
        }
    }

    public static String getUsername() {
        AuthUser authUser = getAuthUser();
        if (authUser != null) {
            return authUser.getUsername();
        } else {
            return "";
        }
    }

    public static Collection<? extends GrantedAuthority> getAuthoritys() {
        Authentication auth = getAuth();
        if (auth != null) {
            return auth.getAuthorities();
        } else {
            return null;
        }
    }

}

