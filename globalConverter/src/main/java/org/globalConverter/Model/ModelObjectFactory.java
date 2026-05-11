package org.globalConverter.Model;

import org.globalConverter.converter.*;
import org.globalConverter.encryption.Caesar;
import org.globalConverter.encryption.Encryption;

import java.util.Optional;

public class ModelObjectFactory {

    public static ModelObject create(String[] args) {
        Converter from    = null;
        Converter to = null;
        String input = null;
        Optional<Encryption> encryption = Optional.empty();
        Optional<Integer> rotatedKey    = Optional.empty();

        // J'aimerais bien centraliser mes erreurs à un endroit pour éviter qu'elles soient toutes dispersées dans mes classes...
        if (args.length < 3)
            throw new IllegalArgumentException("Wrong number of argument! Minimum 3 : From, to and input...");

        for (int i = 0; i < args.length; i++) {
            switch (i) {
                case 0 -> from       = getCorrectConverter(args[0]);
                case 1 -> to         = getCorrectConverter(args[1]);
                case 2 -> input      = getCorrectInput(args[2]);
                case 3 -> encryption = getCorrectEncryption(args[3]);
                case 4 -> rotatedKey = getCorrectRotatedKey(args[4]);
            }
        }

        if(from != null && to != null && input != null ){
                return new ModelObject.Builder(from, to, input)
                        .withEncryptionDecryption(encryption)
                        .withRotatedKey(rotatedKey)
                        .build();
        }

        throw new IllegalArgumentException("Model Object cannot be created");
    }

    private static Converter getCorrectConverter(String from) {
        if (from == null || from.isEmpty())
            throw new IllegalArgumentException("First arguments can't be null or empty.");

        return switch (from) {
            case "binary", "-b"      -> new BinaryConverter();
            case "decimal", "-d"       -> new DecimalConverter();
            case "octal", "-o"       -> new OctalConverter();
            case "text", "-t"     -> new TextConverter();
            case "hexadecimal", "-h" -> new HexadecimalConverter();
            default -> throw new IllegalArgumentException("Argument invalid!");
        };
    }

    private static String getCorrectInput(String input){
        if(InputValidator.isValid(input)) return input;
        throw new IllegalArgumentException("Invalid input!");
    }

    private static Optional<Encryption> getCorrectEncryption(String encryption){
        return switch (encryption){
            case "encrypt", "-e"   -> Optional.of(new Caesar(true));
            case "decrypt", "-d"   -> Optional.of(new Caesar(false));
            default -> throw new IllegalArgumentException("Invalid input!");
        };
    }

    private static Optional<Integer> getCorrectRotatedKey(String rotatedKey){
        if(rotatedKey == null || rotatedKey.isEmpty())throw new IllegalArgumentException("Invalid input, null or empty value are not available...");
        for(char character : rotatedKey.toCharArray()){
            if(!Character.isDigit(character)) throw new IllegalArgumentException("Invalid input, only digit number!");
        }
        return Optional.of(Integer.parseInt(rotatedKey));
    }
}