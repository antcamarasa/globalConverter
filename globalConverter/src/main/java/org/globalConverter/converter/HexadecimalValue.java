package org.globalConverter.converter;

import java.util.Objects;

public enum HexadecimalValue {
    DIGIT_0(0, "0"),
    DIGIT_1(1, "1"),
    DIGIT_2(2, "2"),
    DIGIT_3(3, "3"),
    DIGIT_4(4, "4"),
    DIGIT_5(5, "5"),
    DIGIT_6(6, "6"),
    DIGIT_7(7, "7"),
    DIGIT_8(8, "8"),
    DIGIT_9(9, "9"),
    DIGIT_A(10, "A"),
    DIGIT_B(11, "B"),
    DIGIT_C(12, "C"),
    DIGIT_D(13, "D"),
    DIGIT_E(14, "E"),
    DIGIT_F(15, "F");

    private final int number;
    private final String value;

    HexadecimalValue(int number, String value){
        this.number = number;
        this.value  = value.toUpperCase();
    }

    public static String getValueFromInt(int number){
        for(HexadecimalValue hex : HexadecimalValue.values()){
            if(number == hex.number)return hex.value;
        }
        throw new IllegalArgumentException("Invalid hexadecimal digit! " + number);
    }

    public static int getNumberFromStr(String value){
        for(HexadecimalValue hex : HexadecimalValue.values()){
            if(Objects.equals(hex.value, value.toUpperCase())) return hex.number;
        }

        throw new IllegalArgumentException("Invalid hexadecimal string!" + value);
    }
}
