package com.example.petshopapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String sex;

    private int weight;

    private int cost;

    private int category;

    public Pets(String name, String type, String sex, int weight, int cost, int category) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
        this.cost = cost;
        this.category = category;
    }
}
