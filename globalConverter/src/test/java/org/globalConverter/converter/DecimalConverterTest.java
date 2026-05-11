package org.globalConverter.converter;

import static org.globalConverter.converter.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DecimalConverterTest {
    private final DecimalConverter converter = new DecimalConverter();

    @Test
    void shouldConvertFromTextAllLowercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (LOWERCASE_A_ASCII + i));
            String expectedDecimal = String.valueOf(LOWERCASE_A_ASCII + i);

            assertEquals(List.of(expectedDecimal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllUppercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (UPPERCASE_A_ASCII + i));
            String expectedDecimal = String.valueOf(UPPERCASE_A_ASCII + i);

            assertEquals(List.of(expectedDecimal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllDigits() {
        for (int i = 0; i < DIGIT_SIZE; i++) {
            String digit = String.valueOf(i);
            String expectedDecimal = String.valueOf(ZERO_ASCII + i);

            assertEquals(List.of(expectedDecimal), converter.convertFromText(digit));
        }
    }

    @Test
    void shouldConvertFromTextSpaceCharacter() {
        assertEquals(List.of("32"), converter.convertFromText(" "));
    }

    // ============================================================
    //  Valid inputs - multi-character strings
    // ============================================================

    @Test
    void shouldConvertFromTextMultipleSameLetters() {
        List<String> expected = new ArrayList<>(List.of("97", "97"));
        assertEquals(expected, converter.convertFromText("aa"));
    }

    @Test
    void shouldConvertFromTextHelloWorld() {
        List<String> expected = new ArrayList<>(List.of(
                "104", "101", "108", "108", "111",
                "32",
                "119", "111", "114", "108", "100"
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
}
