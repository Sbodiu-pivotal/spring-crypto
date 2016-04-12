package io.pivotal.apac;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class BadAESCrypto {

    private static final String ALGO = "AES/CBC/PKCS5PADDING";
    private static final byte[] KEYVALUE = "12345678901234567890123456789012".getBytes();
    private static Key key;
    private static Cipher decryptior;
    private static Cipher encryptor;

    // FIXME this methods are not thread safe
    public static String encrypt(String data) throws RuntimeException {
        try {
            key = generateKey();
            encryptor = Cipher.getInstance(ALGO);
            IvParameterSpec iv = new IvParameterSpec(Hex.decodeHex("00000000000000000000000000000000".toCharArray()));
            decryptior = Cipher.getInstance(ALGO);
            encryptor.init(Cipher.ENCRYPT_MODE, key, iv);
            decryptior.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] encVal = encryptor.doFinal(data.getBytes());
            return Base64.encodeBase64String(encVal);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // FIXME this methods are not thread safe
    public static String decrypt(String encryptedData) throws RuntimeException {
        try {
            key = generateKey();
            encryptor = Cipher.getInstance(ALGO);
            IvParameterSpec iv = new IvParameterSpec(Hex.decodeHex("00000000000000000000000000000000".toCharArray()));
            decryptior = Cipher.getInstance(ALGO);
            encryptor.init(Cipher.ENCRYPT_MODE, key, iv);
            decryptior.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] decodedValue = Base64.decodeBase64(encryptedData);
            byte[] value = decryptior.doFinal(decodedValue);
            return new String(value);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Key generateKey() {
        if (key == null) {
            key = new SecretKeySpec(KEYVALUE, "AES");
        }
        return key;
    }

}
