package org.globalConverter;

import org.globalConverter.Model.ModelObject;
import org.globalConverter.converter.*;
import org.globalConverter.encryption.Encryption;

import java.util.Optional;

public class ConversionPipeline {
    private final Converter from;
    private final Converter to;
    private final String input;
    private Optional<Encryption> encryption;
    private Optional<Integer> rotatedKey;

    public ConversionPipeline(ModelObject model){
        this.from       = model.getFrom(); // Hex
        this.to         = model.getTo();   // Oc
        this.input      = model.getInput();
        this.encryption = model.getEncryption();
        this.rotatedKey = model.getRotatedKey();
    }

    public String process(){
        String tmp = this.input;
        tmp = from.convertFromBase(tmp);

        tmp = this.encryption.isPresent() && this.rotatedKey.isPresent() ?
                processEncryptionDecryption(this.encryption.get(), this.rotatedKey.get(), tmp)
                : tmp;

        return to.convertFromText(tmp);
    }

    private String processEncryptionDecryption(Encryption encryption, int key, String str){
        return encryption.isEncrypt() ? encryption.encrypt(str, key) : encryption.decrypt(str, key);
    }
}
