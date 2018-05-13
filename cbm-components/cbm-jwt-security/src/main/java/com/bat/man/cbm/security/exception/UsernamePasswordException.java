package com.bat.man.cbm.security.exception;

public class UsernamePasswordException extends SystemAuthenticationException {

    public UsernamePasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernamePasswordException(String msg) {
        super(msg);
    }
}
