package org.ssafy.pasila.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptService {

    private final AesBytesEncryptor encryptor;

    public String encryptAccount(String account) {
        byte[] encrypt = encryptor.encrypt(account.getBytes(StandardCharsets.UTF_8));
        String a = Base64.encodeBase64String(encrypt);
        return a;
    }

    public String decryptAccount(String encryptString) {
        byte[] decryptBytes = Base64.decodeBase64(encryptString);
        byte[] decrypt = encryptor.decrypt(decryptBytes);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

}