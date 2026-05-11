package org.globalConverter.converter;

public class TextConverter implements Converter{
    private final int BASE  = 10;

    @Override
    public int getBase(){
        return this.BASE;
    }

    @Override
    public String convertFromText(String input){
        return input;
    }

    @Override
    public String convertFromBase(String input){
        return input;
    }
}
