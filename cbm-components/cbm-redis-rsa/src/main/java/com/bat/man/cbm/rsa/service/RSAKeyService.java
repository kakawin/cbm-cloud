package com.bat.man.cbm.rsa.service;

import com.bat.man.cbm.rsa.RSAPublicKey;

import java.security.NoSuchAlgorithmException;

public interface RSAKeyService {

    RSAPublicKey getPublicKey() throws NoSuchAlgorithmException;

    String decrypt(String rsaKeyId, String encryptedText);

}
