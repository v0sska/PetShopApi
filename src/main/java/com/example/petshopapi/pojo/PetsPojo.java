package com.example.petshopapi.pojo;

import com.example.petshopapi.entities.Pets;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PetsPojo {

    private String name;

    private String type;

    private String sex;

    private int weight;

    private int cost;

    private int category;

}
