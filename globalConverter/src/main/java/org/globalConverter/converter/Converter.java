package org.globalConverter.converter;

import org.globalConverter.converter.converterUtils.MathUtils;

import java.util.List;

public interface Converter {
    int getBase();
    String convertFromText(String input);

    default String convertFromBase(String stringInput){
        StringBuilder sb = new StringBuilder();

        List<String> input = List.of(stringInput.split(" "));
        for(String str : input){
            int power = str.length() - 1;
            int total = 0;

            for(int i = 0; i < str.length(); i++){
                String curr = str.substring(i, i + 1);

                int value = getBase() == Base.HEXADECIMAL.getIntValue() ?
                        HexadecimalValue.getNumberFromStr(curr.toUpperCase()) :
                        Integer.parseInt(curr);

                total += value * MathUtils.getPow(this.getBase(), power);

                power--;
            }

            sb.append((char) total);
        }


        return sb.toString();
    }
}
