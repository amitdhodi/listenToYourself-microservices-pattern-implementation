package com.example.listenToYourself.common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Converter<FromObject, ToObject> {
    @Autowired
    private ModelMapper modelMapper;

    abstract ToObject convertFromObj(FromObject fromObj);

    abstract FromObject convertToObj(ToObject toObject);

    protected ModelMapper getModelMapper(){
        return modelMapper;
    }
}
