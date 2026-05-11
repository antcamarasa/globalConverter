package org.globalConverter.converter;

import org.globalConverter.converter.converterUtils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class BinaryConverter implements Converter{
    private static final int BASE = 2;

    @Override
    public int getBase() {
        return BASE;
    }

    @Override
    public String convertFromText(String input) {
        if(!InputValidator.isValid(input))throw new IllegalArgumentException("Input must contain only alphanumeric characters!");
        List<String> output = new ArrayList<>();

        for(int number : input.toCharArray()){
            StringBuilder sb = new StringBuilder();

            BaseDecomposition binaryDecomposition = MathUtils.getExponentAndMaxValidValue(BASE, number);
            int exponent = binaryDecomposition.exponent();
            int maxValue = binaryDecomposition.maxValidValue();

            while(exponent >= 0){
                exponent--;

                if(number >= maxValue)
                {
                    sb.append(1);
                    number -= maxValue;
                }
                else
                {
                  sb.append(0);
                }

                maxValue = maxValue > 0 ? MathUtils.getPow(BASE, exponent) : 0;
            }
            output.add(sb.toString());
        }

        return String.join(" ", output);
    }
}

