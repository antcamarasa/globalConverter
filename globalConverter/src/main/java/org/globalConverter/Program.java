package org.globalConverter;

import org.globalConverter.Model.ModelObject;
import org.globalConverter.Model.ModelObjectFactory;
import org.globalConverter.converter.*;

public class Program {

    public String run(String[] args){
        ModelObject modelObject = ModelObjectFactory.create(args);
        ConversionPipeline pipeline = new ConversionPipeline(modelObject);
        return pipeline.process();
    }
}
