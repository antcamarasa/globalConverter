package org.globalConverter.converter;

import org.globalConverter.converter.converterUtils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class HexadecimalConverter implements Converter{
    private final int BASE = 16;

    @Override
    public int getBase() {
        return BASE;
    }

    @Override
    public String convertFromText(String input) {
        if(!InputValidator.isValid(input)) throw new IllegalArgumentException("Input must contain only alphanumeric characters!");

        List<String> output = new ArrayList<>();
        for(int element : input.toCharArray()){
            StringBuilder sb = new StringBuilder();

            BaseDecomposition hexadecimalDecomposition = MathUtils.getExponentAndMaxValidValue(BASE, element);
            int exponent = hexadecimalDecomposition.exponent();
            int maxValidValue = hexadecimalDecomposition.maxValidValue();

            while(exponent >= 0){
                int digit = element / maxValidValue;
                sb.append(HexadecimalValue.getValueFromInt(digit));

                element -= digit * maxValidValue;
                maxValidValue = maxValidValue / BASE;
                exponent--;
            }

            output.add(sb.toString());
        }

        return String.join(" ", output);
    }
}
