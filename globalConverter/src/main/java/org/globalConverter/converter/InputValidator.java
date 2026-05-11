package org.globalConverter.converter;

public class InputValidator {
    public static boolean isValid(String str){
        return str.chars().allMatch(c -> Character.isLetterOrDigit(c) || c ==  ' ');
    }
}
