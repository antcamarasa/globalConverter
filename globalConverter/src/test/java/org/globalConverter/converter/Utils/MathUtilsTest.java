package org.globalConverter.converter.Utils;

import org.globalConverter.converter.converterUtils.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MathUtilsTest {

    //================================================================================
    // Get Exponent and Max value
    //================================================================================


    //================================================================================
    // Get Pow Test
    //================================================================================
    @ParameterizedTest
    @CsvSource({
            "2, 0, 1",
            "2, 1, 2",
            "2, 2, 4",
            "2, 4, 16"
    })
    void shouldReturnCorrectPowWithBaseTwo(int base, int exponent, int expectedResult){
        Assertions.assertEquals(expectedResult, MathUtils.getPow(base, exponent));
    }

    @ParameterizedTest
    @CsvSource({
            "8, 0, 1",
            "8, 1, 8",
            "8, 2, 64",
            "8, 3, 512",
            "8, 4, 4096",
    })
    void shouldReturnCorrectPowWithBaseEight(int base, int exponent, int expectedResult){
        Assertions.assertEquals(expectedResult, MathUtils.getPow(base, exponent));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 0, 1",
            "10, 1, 10",
            "10, 2, 100",
            "10, 3, 1000",
            "10, 4, 10000",
    })
    void shouldReturnCorrectPowWithBaseTen(int base, int exponent, int expectedResult){
        Assertions.assertEquals(expectedResult, MathUtils.getPow(base, exponent));
    }

    @ParameterizedTest
    @CsvSource({
            "16, 0, 1",
            "16, 1, 16",
            "16, 2, 256",
            "16, 3, 4096",
            "16, 4, 65536"
    })
    void shouldReturnCorrectPowWithBaseSixteen(int base, int exponent, int expectedResult){
        Assertions.assertEquals(expectedResult, MathUtils.getPow(base, exponent));
    }

}
