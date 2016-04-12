package io.pivotal.apac;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static io.pivotal.apac.Encryptor.decrypt;
import static io.pivotal.apac.Encryptor.encrypt;

public class EncryptorTest {

    @Test
    public void shouldDecryptEncryptedMessage() throws InterruptedException, ExecutionException {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        Assert.assertEquals(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")), "Hello World");

    }



}