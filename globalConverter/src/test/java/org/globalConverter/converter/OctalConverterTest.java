package org.globalConverter.converter;

import static org.globalConverter.converter.TestConstants.*;
import org.globalConverter.converter.converterUtils.MathUtils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OctalConverterTest {
    private final OctalConverter converter = new OctalConverter();

    // ============================================================
    //  Valid inputs - single character
    // ============================================================

    @Test
    void shouldConvertFromTextAllLowercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (LOWERCASE_A_ASCII + i));
            String expectedOctal = toOctal(LOWERCASE_A_ASCII + i);

            assertEquals(List.of(expectedOctal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllUppercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (UPPERCASE_A_ASCII + i));
            String expectedOctal = toOctal(UPPERCASE_A_ASCII + i);

            assertEquals(List.of(expectedOctal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllDigits() {
        for (int i = 0; i < DIGIT_SIZE; i++) {
            String digit = String.valueOf(i);
            String expectedOctal = toOctal(ZERO_ASCII + i);

            assertEquals(List.of(expectedOctal), converter.convertFromText(digit));
        }
    }

    @Test
    void shouldConvertFromTextSpaceCharacter() {
        assertEquals(List.of("40"), converter.convertFromText(" "));
    }

    // ============================================================
    //  Valid inputs - multi-character strings
    // ============================================================

    @Test
    void shouldConvertFromTextMultipleSameLetters() {
        List<String> expected = new ArrayList<>(List.of("141", "141"));
        assertEquals(expected, converter.convertFromText("aa"));
    }

    @Test
    void shouldConvertFromTextHelloWorld() {
        List<String> expected = new ArrayList<>(List.of(
                "150", "145", "154", "154", "157",
                "40",
                "167", "157", "162", "154", "144"
        ));

        assertEquals(expected, converter.convertFromText("hello world"));
    }

    // ============================================================
    //  Invalid inputs
    // ============================================================

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
    //  Helpers
    // ============================================================
    private String toOctal(int n) {
        StringBuilder sb = new StringBuilder();

        BaseDecomposition octalDecomposition = MathUtils.getExponentAndMaxValidValue(Base.OCTAL.getIntValue(), n);
        int maxValidNumber = octalDecomposition.maxValidValue();
        int exponent       = octalDecomposition.exponent();

        while (exponent >= 0){
            int octalDigit = n / maxValidNumber;
            sb.append(octalDigit);
            n -= maxValidNumber * octalDigit;


            maxValidNumber = exponent > 0 ? maxValidNumber / Base.OCTAL.getIntValue() : 0;
            exponent--;
        }
        return sb.toString();
    }
}