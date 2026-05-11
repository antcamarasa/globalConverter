package org.globalConverter.converter;

import org.globalConverter.converter.converterUtils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class OctalConverter implements Converter {
    private final int BASE = 8;

    @Override
    public int getBase() {
        return BASE;
    }

    @Override
    public String convertFromText(String input){
        if(!InputValidator.isValid(input))throw new IllegalArgumentException("Input must contain only alphanumeric characters!");

        List<String> output = new ArrayList<>();

        for(int element : input.toCharArray()){
            StringBuilder sb = new StringBuilder();

            BaseDecomposition octalDecomposition = MathUtils.getExponentAndMaxValidValue(this.BASE, element);
            int maxValidNumber = octalDecomposition.maxValidValue();
            int exponent       = octalDecomposition.exponent();

            while (exponent >= 0){
                int octalDigit = element / maxValidNumber;
                sb.append(octalDigit);
                element -= maxValidNumber * octalDigit;


                maxValidNumber = exponent > 0 ? maxValidNumber / BASE : 0;
                exponent--;
            }
            output.add(sb.toString());
        }

        return String.join(" ", output);
    }
}
