package com.example.petshopapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetsPojo {

    private String name;

    private String type;

    private String sex;

    private int weight;

    private int cost;

    private int category;

}
