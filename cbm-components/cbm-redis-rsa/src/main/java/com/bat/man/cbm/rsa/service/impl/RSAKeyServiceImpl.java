package com.bat.man.cbm.rsa.service.impl;

import com.bat.man.cbm.rsa.RSA;
import com.bat.man.cbm.rsa.RSAKey;
import com.bat.man.cbm.rsa.RSAPrivateKey;
import com.bat.man.cbm.rsa.RSAPublicKey;
import com.bat.man.cbm.rsa.service.RSAKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

@Service
public class RSAKeyServiceImpl implements RSAKeyService {

    private static final String RSA_KEY_ID_PREFIX = "RSA-";

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RSAPublicKey getPublicKey() throws NoSuchAlgorithmException {
        RSAPrivateKey privateKey = RSA.keyRSA();
        redisTemplate.opsForValue().set(RSA_KEY_ID_PREFIX + privateKey.getId(), privateKey, RSAKey.DEFAULT_TIME_TO_LIVE_MILLIS, TimeUnit.MILLISECONDS);
        return privateKey.transformToPublicKey();
    }

    @Override
    public String decrypt(String rsaKeyId, String encryptedText) {
        RSAPrivateKey key = (RSAPrivateKey) redisTemplate.opsForValue().get(RSA_KEY_ID_PREFIX + rsaKeyId);
        if (key != null) {
            try {
                PrivateKey privateKey = RSA.generateRSAPrivateKey(key);
                return RSA.decrypt(privateKey, encryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
