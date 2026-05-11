package org.globalConverter.converter;

// Strategy enum!

public enum Base {
    BINARY("Binary", 2),
    DECIMAL("Decimal", 10),
    OCTAL("Octal", 8),
    HEXADECIMAL("Hexadecimal", 16),
    TEXT("Text", 10);

    private final String strValue;
    private final int intValue;

    Base(String strValue, int intValue){
        this.strValue = strValue;
        this.intValue = intValue;
    }

    public String getStrValue(){return this.strValue;}
    public int getIntValue(){return this.intValue;}

    public Converter createConverter(){
        return switch (this){
            case BINARY      -> new BinaryConverter();
            case OCTAL       -> new OctalConverter();
            case DECIMAL     -> new DecimalConverter();
            case HEXADECIMAL -> new HexadecimalConverter();
            default -> throw new IllegalArgumentException("Wrong input;");
        };
    }
}
