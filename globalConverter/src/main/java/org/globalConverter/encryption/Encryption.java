package org.globalConverter.encryption;

public interface Encryption {
    boolean isEncrypt();
    String encrypt(String original, int key);
    String decrypt(String original, int key);
}
