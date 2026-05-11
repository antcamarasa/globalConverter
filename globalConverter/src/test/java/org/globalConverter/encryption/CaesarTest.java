package org.globalConverter.encryption;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CaesarTest {
    private final Caesar caesar = new Caesar(true);

    // ________________________________________________________________________________________________
    // Rotate and revert
    // ________________________________________________________________________________________________
    @Test
    void shouldReturnTheCorrectAsciiRotated(){
        Assertions.assertEquals(100, caesar.rotate(122, 30, Borne.LOWER_ALPHABETIC));
        Assertions.assertEquals(98, caesar.rotate(97, 1, Borne.LOWER_ALPHABETIC));
        Assertions.assertEquals(99, caesar.rotate(120, 5, Borne.LOWER_ALPHABETIC));
    }

    @Test
    void shouldReturnTheCorrectAsciiRevert(){
        Assertions.assertEquals(122, caesar.revert(100, 30, Borne.LOWER_ALPHABETIC));
        Assertions.assertEquals(97, caesar.revert(98, 1, Borne.LOWER_ALPHABETIC));
        Assertions.assertEquals(120, caesar.revert(99, 5, Borne.LOWER_ALPHABETIC));
    }

    @Test
    void shouldReturnTheCorrectAsciiRotatedUpperCase(){
        Assertions.assertEquals(68, caesar.rotate(90, 4, Borne.UPPER_ALPHABETIC));
        Assertions.assertEquals(66, caesar.rotate(65, 1, Borne.UPPER_ALPHABETIC));
        Assertions.assertEquals(67, caesar.rotate(88, 5, Borne.UPPER_ALPHABETIC));
    }

    @Test
    void shouldReturnTheCorrectAsciiRevertUpperCase(){
        Assertions.assertEquals(90, caesar.revert(68, 4, Borne.UPPER_ALPHABETIC));
        Assertions.assertEquals(65, caesar.revert(66, 1, Borne.UPPER_ALPHABETIC));
        Assertions.assertEquals(88, caesar.revert(67, 5, Borne.UPPER_ALPHABETIC));
    }
    @Test
    void shouldReturnTheCorrectAsciiRotatedDigit(){
        Assertions.assertEquals(51, caesar.rotate(57, 4, Borne.DIGIT));
        Assertions.assertEquals(49, caesar.rotate(48, 1, Borne.DIGIT));
        Assertions.assertEquals(50, caesar.rotate(55, 5, Borne.DIGIT));
    }

    @Test
    void shouldReturnTheCorrectAsciiRevertDigit(){
        Assertions.assertEquals(57, caesar.revert(51, 4, Borne.DIGIT));
        Assertions.assertEquals(48, caesar.revert(49, 1, Borne.DIGIT));
        Assertions.assertEquals(55, caesar.revert(50, 5, Borne.DIGIT));
    }


    @Test
    void shouldReturnTheCorrectEncryptingValue(){
        String expected =  "khoor";
        Assertions.assertEquals(expected, caesar.encrypt("hello", 3));
    }

    @Test
    void shouldReturnTheCorrectDecryptingValue(){
        String expected = "hello";
        Assertions.assertEquals(expected, caesar.decrypt("khoor", 3));
    }

    @Test
    void shouldReturnTheCorrectEncryptingPhraseValue(){
        String expected =  "Khoor zruog";
        Assertions.assertEquals(expected, caesar.encrypt("Hello world", 3));
    }

    @Test
    void shouldReturnTheCorrectDecryptingPhraseValue(){
        String expected =  "Hello world";
        Assertions.assertEquals(expected, caesar.decrypt("Khoor zruog", 3));
    }
}
