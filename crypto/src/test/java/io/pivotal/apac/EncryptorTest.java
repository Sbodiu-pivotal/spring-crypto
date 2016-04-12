package io.pivotal.apac;

import org.junit.Test;

import static io.pivotal.apac.Encryptor.decrypt;
import static io.pivotal.apac.Encryptor.encrypt;

public class EncryptorTest {

    @Test
    public void shouldDecryptEncryptedMessage() {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")));
    }

}