package com.bat.man.cbm.security.exception;

public class UsernameException extends UsernamePasswordException{

    public UsernameException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameException(String msg) {
        super(msg);
    }
}
