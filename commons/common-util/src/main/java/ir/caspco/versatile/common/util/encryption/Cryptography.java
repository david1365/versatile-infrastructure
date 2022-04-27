package ir.caspco.versatile.common.util.encryption;

import ir.caspco.versatile.common.util.exceptions.AliasIncorrectException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import sun.security.rsa.RSACore;
import sun.security.rsa.RSAKeyFactory;
import sun.security.rsa.RSAPadding;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAKey;
import java.util.Base64;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public abstract class Cryptography {

    private char[] password;

    private String alias;

    private KeyStore keyStore;


    @SneakyThrows
    public Cryptography(String path, char[] password, String alias) {

        this.password = password;
        this.alias = alias;


        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        try (InputStream keyStoreData = new FileInputStream(path)) {

            keyStore.load(keyStoreData, password);
        }
    }

    protected abstract Cipher cipher();

    protected abstract Signature signature();

    @SneakyThrows
    public KeyPair keyPair() {

        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password);

        Certificate certificate = keyStore.getCertificate(alias);
        if (privateKey == null || certificate == null) {

            throw new AliasIncorrectException();
        }

        PublicKey publicKey = certificate.getPublicKey();


        return new KeyPair(publicKey, privateKey);
    }

    @SneakyThrows
    public String sign(String message) {

        Signature privateSignature = signature();
        privateSignature.initSign(keyPair().getPrivate());
        privateSignature.update(message.getBytes(StandardCharsets.UTF_8));

        final byte[] signature = privateSignature.sign();

        return Base64.getMimeEncoder().encodeToString(signature);
    }


    @SneakyThrows
    public boolean verify(String signature, String message) {

        final byte[] signatureBytes = Base64.getMimeDecoder().decode(signature);

        Signature publicSignature = signature();
        publicSignature.initVerify(keyPair().getPublic());
        publicSignature.update(message.getBytes(StandardCharsets.UTF_8));

        return publicSignature.verify(signatureBytes);
    }

    @SneakyThrows
    public String encrypt(String message) {

        Cipher encryptCipher = cipher();
        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair().getPublic());

        byte[] data = message.getBytes(StandardCharsets.UTF_8);

        byte[] dataReturn = encrypt(encryptCipher, data);

        return Base64.getMimeEncoder().encodeToString(dataReturn);
    }

    @SneakyThrows
    public String decrypt(String cipherText) {

        final byte[] cipherData = Base64.getMimeDecoder().decode(cipherText);

        Cipher decryptCipher = cipher();
        decryptCipher.init(Cipher.DECRYPT_MODE, keyPair().getPrivate());


        byte[] data = decrypt(cipherData, decryptCipher);

        return new String(data, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private byte[] encrypt(Cipher encryptCipher, byte[] data) {

        int byteLength = getByteLength();
        RSAPadding padding = RSAPadding.getInstance(Cipher.ENCRYPT_MODE, byteLength, new SecureRandom());
        final int MAX_ENCRYPT_BLOCK = padding.getMaxDataSize();

        return cryptography(encryptCipher, data, MAX_ENCRYPT_BLOCK);
    }

    @SneakyThrows
    private byte[] decrypt(byte[] cipherData, Cipher decryptCipher) {

        final int MAX_DECRYPT_BLOCK = getByteLength();

        return cryptography(decryptCipher, cipherData, MAX_DECRYPT_BLOCK);
    }

    @SneakyThrows
    private byte[] cryptography(Cipher encryptCipher, byte[] data, int maxBlock) {

        byte[] dataReturn = new byte[0];

        for (int i = 0; i < data.length; i += maxBlock) {

            byte[] subData = ArrayUtils.subarray(data, i, i + maxBlock);
            byte[] cipherSubData = encryptCipher.doFinal(subData);

            dataReturn = ArrayUtils.addAll(dataReturn, cipherSubData);
        }

        return dataReturn;
    }

    @SneakyThrows
    private int getByteLength() {

        RSAKey rsaPublicKey = RSAKeyFactory.toRSAKey(keyPair().getPublic());

        return RSACore.getByteLength(rsaPublicKey.getModulus());
    }
}
