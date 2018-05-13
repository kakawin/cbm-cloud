package com.bat.man.cbm.security.exception;

public class PasswordException extends UsernamePasswordException {

    public PasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public PasswordException(String msg) {
        super(msg);
    }
}
