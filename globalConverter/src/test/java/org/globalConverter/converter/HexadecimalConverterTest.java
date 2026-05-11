package org.globalConverter.converter;

import static org.globalConverter.converter.TestConstants.*;
import org.globalConverter.converter.converterUtils.MathUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexadecimalConverterTest {
    private final HexadecimalConverter converter = new HexadecimalConverter();


    // ============================================================
    //  Valid inputs - single character
    // ============================================================
    @Test
    void shouldConvertFromTextAllLowercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (LOWERCASE_A_ASCII + i));
            String expectedHexadecimal = toHexadecimal(LOWERCASE_A_ASCII + i);

            assertEquals(List.of(expectedHexadecimal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllUppercaseLetters() {
        for (int i = 0; i < ALPHABETIC_SIZE; i++) {
            String letter = String.valueOf((char) (UPPERCASE_A_ASCII + i));
            String expectedHexadecimal = toHexadecimal(UPPERCASE_A_ASCII + i);

            assertEquals(List.of(expectedHexadecimal), converter.convertFromText(letter));
        }
    }

    @Test
    void shouldConvertFromTextAllDigits() {
        for (int i = 0; i < DIGIT_SIZE; i++) {
            String digit = String.valueOf(i);
            String expectedHexadecimal = toHexadecimal(ZERO_ASCII + i);

            assertEquals(List.of(expectedHexadecimal), converter.convertFromText(digit));
        }
    }

    @Test
    void shouldConvertFromTextSpaceCharacter() {
        assertEquals(List.of("20"), converter.convertFromText(" "));
    }

    // ============================================================
    //  Valid inputs - multi-character strings
    // ============================================================

    @Test
    void shouldConvertFromTextMultipleSameLetters() {
        List<String> expected = new ArrayList<>(List.of("61", "61"));
        assertEquals(expected, converter.convertFromText("aa"));
    }

    @Test
    void shouldConvertFromTextHelloWorld() {
        List<String> expected = new ArrayList<>(List.of(
                "68", "65", "6C", "6C", "6F",
                "20",
                "77", "6F", "72", "6C", "64"
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
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> converter.convertFromText(invalidInput)
            );
        }
    }

    // ============================================================
    // Test - Convert Form Base To Text
    // ============================================================
    public String toHexadecimal(int num){
        BaseDecomposition hexadecimalDecomposition = MathUtils.getExponentAndMaxValidValue(Base.HEXADECIMAL.getIntValue(), num);
        int exponent = hexadecimalDecomposition.exponent();
        int maxValue = hexadecimalDecomposition.maxValidValue();

        StringBuilder sb = new StringBuilder();
        while (exponent >= 0){
            int digit = num / maxValue;
            sb.append(HexadecimalValue.getValueFromInt(digit));

            num -= digit * maxValue;
            maxValue = maxValue / Base.HEXADECIMAL.getIntValue();
            exponent--;
        }

        return sb.toString();
    }

}
