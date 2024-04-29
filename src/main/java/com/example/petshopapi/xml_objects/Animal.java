package com.example.petshopapi.xml_objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Animal {

   @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "sex", isAttribute = true)
    private String sex;

    @JacksonXmlProperty(localName = "weight", isAttribute = true)
    private Integer weight;

    @JacksonXmlProperty(localName = "cost", isAttribute = true)
    private Integer cost;

    @JacksonXmlProperty(localName = "category", isAttribute = true)
    private int category;

}
