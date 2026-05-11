package org.globalConverter.encryption;

public enum Borne {
    LOWER_ALPHABETIC(97, 122),
    UPPER_ALPHABETIC(65, 90),
    DIGIT(48, 57),
    SPECIAL_FOR_SPACE(32, 32);

    private final int lower;
    private final int upper;

    Borne(int lower, int upper){
        this.lower = lower;
        this.upper = upper;
    }

    public int getLower(){
        return this.lower;
    }
    public int getUpper(){
        return this.upper;
    }

    public static Borne getBorne(char ascii){
       if(ascii >= LOWER_ALPHABETIC.lower && ascii <= LOWER_ALPHABETIC.upper)return LOWER_ALPHABETIC;
       else if(ascii >= UPPER_ALPHABETIC.lower && ascii <= UPPER_ALPHABETIC.upper) return UPPER_ALPHABETIC;
       else if(ascii >= DIGIT.lower && ascii <= DIGIT.upper)return DIGIT;
       else if(ascii >= SPECIAL_FOR_SPACE.getLower() && ascii <= SPECIAL_FOR_SPACE.getUpper())return SPECIAL_FOR_SPACE;
       else throw new IllegalArgumentException("");
    }
}
