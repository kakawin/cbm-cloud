package com.bat.man.cbm.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SystemAuthenticationException extends AuthenticationException {

    public SystemAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public SystemAuthenticationException(String msg) {
        super(msg);
    }
}
