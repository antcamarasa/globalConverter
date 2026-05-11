package org.globalConverter.encryption;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BorneTest {

    @Test
    void shouldReturnLowerAlphabeticBorne(){
        Assertions.assertEquals(Borne.LOWER_ALPHABETIC, Borne.getBorne('a'));
        Assertions.assertEquals(Borne.LOWER_ALPHABETIC, Borne.getBorne('o'));
        Assertions.assertEquals(Borne.LOWER_ALPHABETIC, Borne.getBorne('z'));
    }

    @Test
    void shouldReturnUpperAlphabeticBorne(){
        Assertions.assertEquals(Borne.UPPER_ALPHABETIC, Borne.getBorne('A'));
        Assertions.assertEquals(Borne.UPPER_ALPHABETIC, Borne.getBorne('O'));
        Assertions.assertEquals(Borne.UPPER_ALPHABETIC, Borne.getBorne('Z'));
    }

    @Test
    void shouldReturnDigitBorne(){
        Assertions.assertEquals(Borne.DIGIT, Borne.getBorne('1'));
        Assertions.assertEquals(Borne.DIGIT, Borne.getBorne('0'));
        Assertions.assertEquals(Borne.DIGIT, Borne.getBorne('9'));
    }


}
