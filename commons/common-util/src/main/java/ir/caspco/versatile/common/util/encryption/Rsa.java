package ir.caspco.versatile.common.util.encryption;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.security.Signature;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class Rsa extends Cryptography {

    public Rsa(String path, char[] password, String alias) {

        super(path, password, alias);
    }

    @SneakyThrows
    @Override
    public Cipher cipher() {

        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    @SneakyThrows
    @Override
    public Signature signature() {

        return Signature.getInstance("SHA256withRSA");
    }
}
