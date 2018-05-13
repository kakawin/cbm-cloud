package com.bat.man.cbm.system.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public abstract class PasswordManager {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder(4, new SecureRandom("com.bat.man.cbm".getBytes()));

    public static String encode(String origin) {
        return encoder.encode(origin);
    }

    public static boolean matches(String origin, String encoded) {
        return encoder.matches(origin, encoded);
    }
}
