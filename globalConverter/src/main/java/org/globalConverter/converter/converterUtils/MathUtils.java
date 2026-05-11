package org.globalConverter.converter.converterUtils;

import org.globalConverter.converter.BaseDecomposition;

public class MathUtils {

    public static BaseDecomposition getExponentAndMaxValidValue(int base, int number){
        int exponent = 0;
        while(number >= MathUtils.getPow(base, exponent + 1)){exponent++;}
        return  new BaseDecomposition(exponent, getPow(base, exponent));
    }

    public static int getPow(int base, int exponent){
        int result = 1;
        for(int i = 0; i < exponent; i++){
            result *= base;
        }
        return result;
    }


}
