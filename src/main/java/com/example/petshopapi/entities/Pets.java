package com.example.petshopapi.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
    @Schema
    private Long id;

    @Column(name = "name")
    @Schema
    private String name;

    @Column(name = "type")
    @Schema
    private String type;

    @Column(name = "sex")
    @Schema
    private String sex;

    @Column(name = "weight")
    @Schema
    private int weight;

    @Column(name = "cost")
    @Schema
    private int cost;

    @Column
    @Schema(description = "Name of pet")
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
