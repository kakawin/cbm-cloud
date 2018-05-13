package com.bat.man.cbm.rsa;

public class RSAPublicKey extends RSAKey {

    private static final long serialVersionUID = 7987664241230431372L;

    private String publicKey;

    public RSAPublicKey() {

    }

    public RSAPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
