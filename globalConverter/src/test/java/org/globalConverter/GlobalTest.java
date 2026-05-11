package org.globalConverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlobalTest {
    private final Program program = new Program();

    @Test
    void shouldReturnCorrectTextToDecimal(){
        String output = "104 101 108 108 111 32 119 111 114 108 100";
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-d";
        args[2] = "hello world";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnCorrectTextToBinary(){
        String output = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-b";
        args[2] = "hello world";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectHexBaseConversion(){
        String output = "68 65 6C 6C 6F 20 77 6F 72 6C 64";
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-h";
        args[2] = "hello world";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectOctalBaseConversion(){
        String output = "150 145 154 154 157 40 167 157 162 154 144";
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-o";
        args[2] = "hello world";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectBinaryBaseConversion(){
        String output = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-b";
        args[2] = "hello world";
        Assertions.assertEquals(output, program.run(args));
    }

    // ____________________________________________________________________________________________________________
    // Reverse from binary/octal/hexa to text
    // ____________________________________________________________________________________________________________
    @Test
    void shouldReturnTheCorrectTextFRomBinaryConversion(){
        String output = "hello world";
        String[] args = new String[3];
        args[0] = "-b";
        args[1] = "-t";
        args[2] = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectTextFromOctalConversion(){
        String output = "hello world";
        String[] args = new String[3];
        args[0] = "-o";
        args[1] = "-t";
        args[2] = "150 145 154 154 157 40 167 157 162 154 144";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectTextFromHexadecimalConversion(){
        String output = "hello world";
        String[] args = new String[3];
        args[0] = "-h";
        args[1] = "-t";
        args[2] = "68 65 6C 6C 6F 20 77 6F 72 6C 64";
        Assertions.assertEquals(output, program.run(args));
    }

    // ____________________________________________________________________________________________________________
    // Process Encrypt without middle conversion
    // ____________________________________________________________________________________________________________
    @Test
    void shouldReturnTheCorrectBinaryEncryptingFromText(){
        String output = "1101011 1101000 1101111 1101111 1110010 100000 1111010 1110010 1110101 1101111 1100111";
        String[] args = new String[5];
        args[0] = "-t";
        args[1] = "-b";
        args[2] = "hello world";
        args[3] = "-e";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectOctalEncryptingFromText(){
        String output = "153 150 157 157 162 40 172 162 165 157 147";
        String[] args = new String[5];
        args[0] = "-t";
        args[1] = "-o";
        args[2] = "hello world";
        args[3] = "-e";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnTheCorrectHexEncryptingFromText(){
        String output = "6B 68 6F 6F 72 20 7A 72 75 6F 67";
        String[] args = new String[5];
        args[0] = "-t";
        args[1] = "-h";
        args[2] = "hello world";
        args[3] = "-e";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }
    // ____________________________________________________________________________________________________________
    // Process Decrypt without middle conversion
    // ____________________________________________________________________________________________________________
    @Test
    void shouldReturnHelloWorldFromBinaryWithCaesarDecryption(){
        String output = "hello world";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-t";
        args[2] = "1101011 1101000 1101111 1101111 1110010 100000 1111010 1110010 1110101 1101111 1100111";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnHelloWorldFromOctalWithCaesarDecryption(){
        String output = "hello world";
        String[] args = new String[5];
        args[0] = "-o";
        args[1] = "-t";
        args[2] = "153 150 157 157 162 40 172 162 165 157 147";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnHelloWorldFromHexadecimalWithCaesarDecryption(){
        String output = "hello world";
        String[] args = new String[5];
        args[0] = "-h";
        args[1] = "-t";
        args[2] = "6b 68 6f 6f 72 20 7a 72 75 6f 67";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    // ____________________________________________________________________________________________________________
    // Process Convert with middle conversion & no encryption
    // ____________________________________________________________________________________________________________
    @Test
    void shouldReturnCorrectConversionWithMiddleConversionOctalDestination(){
        String expected = "150 145 154 154 157 40 167 157 162 154 144";
        String[] args = new String[3];
        args[0] = "-b";
        args[1] = "-o";
        args[2] = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        Assertions.assertEquals(expected, program.run(args));
    }

    @Test
    void shouldReturnCorrectConversionWithMiddleConversionHexDestination(){
        String expected = "68 65 6C 6C 6F 20 77 6F 72 6C 64";
        String[] args = new String[3];
        args[0] = "-b";
        args[1] = "-h";
        args[2] = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        Assertions.assertEquals(expected, program.run(args));
    }

    @Test
    void shouldReturnCorrectConversionWithMiddleConversionBinaryDestinationFromOctal(){
        String expected = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        String[] args = new String[3];
        args[0] = "-o";
        args[1] = "-b";
        args[2] = "150 145 154 154 157 40 167 157 162 154 144";
        Assertions.assertEquals(expected, program.run(args));
    }

    @Test
    void shouldReturnCorrectConversionWithMiddleConversionBinaryDestinationFromHex(){
        String expected = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        String[] args = new String[3];
        args[0] = "-h";
        args[1] = "-b";
        args[2] = "68 65 6C 6C 6F 20 77 6F 72 6C 64";
        Assertions.assertEquals(expected, program.run(args));
    }

    // ____________________________________________________________________________________________________________
    // Process Encrypt with middle conversion
    // ____________________________________________________________________________________________________________

    @Test
    void shouldReturnOctalFromBinaryWithCaesarEncryption(){
        String output = "153 150 157 157 162 40 172 162 165 157 147";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-o";
        args[2] = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        args[3] = "-e";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnHexadecimalFromBinaryWithCaesarEncryption(){
        String output = "6B 68 6F 6F 72 20 7A 72 75 6F 67";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-h";
        args[2] = "1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100";
        args[3] = "-e";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    // ____________________________________________________________________________________________________________
    // Process Decrypt with middle conversion
    // ____________________________________________________________________________________________________________
    @Test
    void shouldReturnBinaryFromOctalWithCaesarDecryption(){
        String output = "150 145 154 154 157 40 167 157 162 154 144";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-o";
        args[2] = "1101011 1101000 1101111 1101111 1110010 100000 1111010 1110010 1110101 1101111 1100111";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnOctalFromBinaryWithCaesarDecryption(){
        String output = "150 145 154 154 157 40 167 157 162 154 144";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-o";
        args[2] = "1101011 1101000 1101111 1101111 1110010 100000 1111010 1110010 1110101 1101111 1100111";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }

    @Test
    void shouldReturnHexlFromBinaryWithCaesarDecryption(){
        String output = "68 65 6C 6C 6F 20 77 6F 72 6C 64";
        String[] args = new String[5];
        args[0] = "-b";
        args[1] = "-h";
        args[2] = "1101011 1101000 1101111 1101111 1110010 100000 1111010 1110010 1110101 1101111 1100111";
        args[3] = "-d";
        args[4] = "3";
        Assertions.assertEquals(output, program.run(args));
    }
}
