package org.globalConverter.encryption;

import java.util.Objects;

public class Caesar implements Encryption {
    boolean encrypt;

    public Caesar(boolean encrypt){
        this.encrypt = encrypt;
    }

    // Pour chaque caractère je dois connaitre sa borne min et sa borne max.
    // -> lowerCase
    // -> upperCase
    // -> digit

    @Override
    public String encrypt(String original, int key){
        StringBuilder encrypted = new StringBuilder();

        for(char ascii : original.toCharArray()){
            Borne borne = Borne.getBorne(ascii);
            if(borne == Borne.SPECIAL_FOR_SPACE){
                encrypted.append(ascii);
                continue;
            }

            int rotatedAscii = ascii + key > borne.getUpper() ? rotate(ascii, key, borne) : (char) ascii + key;
            encrypted.append((char) rotatedAscii);
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String original, int key){
        StringBuilder decrypted = new StringBuilder();

        for(char ascii : original.toCharArray()){
            Borne borne = Borne.getBorne(ascii);

            if(borne == Borne.SPECIAL_FOR_SPACE){
                decrypted.append(ascii);
                continue;
            }

            int revertAscii = ascii - key > borne.getLower() ? ascii - key : revert(ascii, key, borne);
            decrypted.append((char)revertAscii);
        }

        return decrypted.toString();
    }

    // Easier implementation method
    public int rotate(int ascii, int key, Borne borne){
        for(int i = 0; i < key; i++){
            if(ascii + 1 > borne.getUpper()){
                ascii = borne.getLower();
                continue;
            }
            ascii += 1;
        }

        return ascii;
    };
    public int revert(int ascii, int key, Borne borne){
        for(int i = 0; i < key; i++){
            if(ascii - 1 < borne.getLower()){
                ascii = borne.getUpper();
                continue;
            }
            ascii -= 1;
        }
        return ascii;
    }


    // Getter
    @Override
    public boolean isEncrypt() {return encrypt;}

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Caesar caesar)) return false;
        return this.encrypt == caesar.isEncrypt();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.encrypt);
    }
}
