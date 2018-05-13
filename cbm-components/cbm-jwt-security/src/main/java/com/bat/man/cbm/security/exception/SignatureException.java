package com.bat.man.cbm.security.exception;

public class SignatureException extends SystemAuthenticationException{

    public SignatureException(String msg, Throwable t) {
        super(msg, t);
    }

    public SignatureException(String msg) {
        super(msg);
    }
}
