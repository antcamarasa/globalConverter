package org.globalConverter.converter;

import java.util.ArrayList;
import java.util.List;

public class DecimalConverter implements Converter{
    private final int BASE = 10;

    @Override
    public int getBase() {
        return BASE;
    }

    @Override
    public String convertFromText(String input){
        List<String> output = new ArrayList<>();

        if(!InputValidator.isValid(input)){
            throw  new IllegalArgumentException("Invalid input");
        }

        for(int c : input.toCharArray()){
            output.add(String.valueOf(c));
        }

        return String.join(" ", output);
    }
}
