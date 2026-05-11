package org.globalConverter.Model;

import org.globalConverter.converter.Base;
import org.globalConverter.converter.DecimalConverter;
import org.globalConverter.converter.HexadecimalConverter;
import org.globalConverter.encryption.Caesar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ModelObjectFactoryTest {

    @Test
    void withoutEncryption(){
        ModelObject expected = new ModelObject.Builder(new DecimalConverter(), new HexadecimalConverter(), "hello")
                .build();

        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "-h";
        args[2] = "hello";

        Assertions.assertEquals(expected, ModelObjectFactory.create(args));
    }

    @Test
    void withEncryption(){
        ModelObject expected = new ModelObject.Builder(new DecimalConverter(), new HexadecimalConverter(), "hello")
                .withEncryptionDecryption(Optional.of(new Caesar(true)))
                .withRotatedKey(Optional.of(3))
                .build();

        String[] args = new String[5];
        args[0] = "-t";
        args[1] = "-h";
        args[2] = "hello";
        args[3] = "-e";
        args[4] = "3";

        Assertions.assertEquals(expected, ModelObjectFactory.create(args));
    }
}
