package com.example.petshopapi.xml_objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Animal {

   @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "sex", isAttribute = true)
    private String sex;

    @JacksonXmlProperty(localName = "weight", isAttribute = true)
    private int weight;

    @JacksonXmlProperty(localName = "cost", isAttribute = true)
    private int cost;

    @JacksonXmlProperty(localName = "category", isAttribute = true)
    private int category;

}
