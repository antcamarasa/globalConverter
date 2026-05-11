package org.globalConverter.converter;

import static org.globalConverter.converter.TestConstants.*;
import org.globalConverter.converter.converterUtils.MathUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinaryConverterTest {
    private final BinaryConverter converter = new BinaryConverter();

    // ============================================================
    // Test - Convert Form Text To Base
    // ============================================================
    @Test
    void simpleForDebug(){
        var result = converter.convertFromText("a");
        System.out.println("Result : " + result);
    }

    // ______________________________________
    //  Valid inputs - single character
    @Test
    void shouldConvertFromTextAllLowercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (LOWERCASE_A_ASCII + i));
            String expectedBinary = toBinary(LOWERCASE_A_ASCII + i);

            assertEquals(List.of(expectedBinary), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllUppercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (UPPERCASE_A_ASCII + i));
            String expectedBinary = toBinary(UPPERCASE_A_ASCII + i);

            assertEquals(List.of(expectedBinary), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllDigits() {
        for (int i = 0; i < DIGIT_SIZE; i++) {
            String digit = String.valueOf(i);
            String expectedBinary = toBinary(ZERO_ASCII + i);

            assertEquals(List.of(expectedBinary), converter.convertFromText(digit));
        }
    }

    @Test
    void shouldConvertFromTextSpaceCharacter() {
        assertEquals(List.of("100000"), converter.convertFromText(" "));
    }

    // ______________________________________
    //  Valid inputs - multi-character strings
    @Test
    void shouldConvertFromTextMultipleSameLetters() {
        List<String> expected = new ArrayList<>(List.of("1100001", "1100001"));
        assertEquals(expected, converter.convertFromText("aa"));
    }

    @Test
    void shouldConvertFromTextHelloWorld() {
        List<String> expected = new ArrayList<>(List.of(
                "1001000",
                "1100101",
                "1101100",
                "1101100",
                "1101111",
                "100000",
                "1010111",
                "1101111",
                "1110010",
                "1101100",
                "1100100"
        ));

        assertEquals(expected, converter.convertFromText("Hello World"));
    }

    // ________________________________________
    //  Invalid inputs
    @Test
    void shouldThrowExceptionForNonAlphanumericInput() {
        String[] invalidInputs = {
                "abc!",
                "test@",
                "hello#world",
                "123$",
                "test\n",
                "abc-123"
        };

        for (String invalidInput : invalidInputs) {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> converter.convertFromText(invalidInput)
            );
        }
    }

    // ============================================================
    // Test - Convert Form Base To Text
    // ============================================================
    @Test
    void testConvertFromBaseForDebug(){
        assertEquals("a", converter.convertFromBase("01100001"));
    }

    @Test
    void testConvertFromBaseHelloWorld(){
        assertEquals("hello world", converter.convertFromBase(
                "01101000 01100101 01101100 01101100 01101111 00100000 01110111 01101111 01110010 01101100 01100100"
                ));
    }

    // ============================================================
    //  Helpers
    // ============================================================
    private String toBinary(int n) {
        StringBuilder sb = new StringBuilder();

        BaseDecomposition binaryDecomposition = MathUtils.getExponentAndMaxValidValue(Base.BINARY.getIntValue(), n);
        int exponent      = binaryDecomposition.exponent();
        int maxValue = binaryDecomposition.maxValidValue();

        while(exponent >= 0){
            exponent--;
            if(n >= maxValue){
                sb.append(1);
                n -= maxValue;
            }
            else{
                sb.append(0);
            }
            maxValue = maxValue > 0 ? MathUtils.getPow(Base.BINARY.getIntValue(), exponent) : 0;
        }

        return sb.toString();
    }
}