package com.example.petshopapi.xml_objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@JacksonXmlRootElement(localName = "animals")
@ToString
public class Animals {

    @JacksonXmlProperty(localName = "animal")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Animal> animals;

}
