package com.bat.man.cbm.rsa;

public class RSAPrivateKey extends RSAPublicKey {

    private static final long serialVersionUID = -369498672622835471L;

    private String privateKey;

    public RSAPrivateKey(RSAPublicKey publicKey, String privateKey) {
        super.setPublicKey(publicKey.getPublicKey());
        super.setId(publicKey.getId());
        super.setTimestamp(publicKey.getTimestamp());
        super.setTtl(publicKey.getTtl());
        this.privateKey = privateKey;
    }

    public RSAPublicKey transformToPublicKey() {
        RSAPublicKey publicKey = new RSAPublicKey();
        publicKey.setPublicKey(this.getPublicKey());
        publicKey.setId(this.getId());
        publicKey.setTimestamp(this.getTimestamp());
        publicKey.setTtl(this.getTtl());
        return publicKey;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}
