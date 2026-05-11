package org.globalConverter.Model;

import org.globalConverter.converter.Base;
import org.globalConverter.converter.Converter;
import org.globalConverter.encryption.Encryption;

import java.util.Objects;
import java.util.Optional;

public class ModelObject {
    private final Converter from;
    private final Converter to;
    private final String input;
    private final Optional<Encryption> encryption;
    private final Optional<Integer> rotatedKey;


    private ModelObject(Builder builder){
        this.from       = builder.getFrom();
        this.to         = builder.getTo();
        this.input      = builder.getInput();
        this.encryption = builder.getEncryption();
        this.rotatedKey = builder.getRotatedKey();
    }

    static class Builder {
        private Converter from;
        private Converter to;
        private String input;
        private Optional<Encryption> encryption;
        private Optional<Integer> rotatedKey;

        public Builder(Converter from, Converter to, String input){
            this.from  = from;
            this.to    = to;
            this.input = input;
        }


        public Builder withEncryptionDecryption(Optional<Encryption> encryptionDecryption){
            this.encryption = encryptionDecryption;
            return this;
        }

        public Builder withRotatedKey(Optional<Integer> rotatedKey){
            this.rotatedKey = rotatedKey;
            return this;
        }

        public ModelObject build(){
            return new ModelObject(this);
        }

        // Getter
        public Converter getFrom(){return this.from;}
        public Converter getTo(){return this.to;}
        public String getInput(){return this.input;}
        public Optional<Encryption> getEncryption(){return this.encryption;}
        public Optional<Integer> getRotatedKey(){return this.rotatedKey;}
    }



    public Converter getFrom() {return from;}
    public Converter getTo() {return to;}
    public String getInput() {return input;}
    public Optional<Encryption> getEncryption() {return encryption;}
    public Optional<Integer> getRotatedKey() {return rotatedKey;}



    @Override
    public boolean equals(Object other){
        if(!(other instanceof ModelObject model)) return false;
        if(this.from.getBase() != model.getFrom().getBase()) return false;
        if(this.to.getBase() != model.getTo().getBase()) return false;
        if(!Objects.equals(this.input, model.getInput()))return false;
        if(!Objects.equals(this.encryption, model.getEncryption())) return false;
        if(!Objects.equals(this.rotatedKey, model.getRotatedKey()))return false;
        return true;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.from.getBase(), this.to.getBase(), this.input, this.encryption, this.rotatedKey);
    }

}
