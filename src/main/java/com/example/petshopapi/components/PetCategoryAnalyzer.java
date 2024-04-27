package com.example.petshopapi.components;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.pojo.PetsPojo;
import org.springframework.stereotype.Component;

@Component
public class PetCategoryAnalyzer implements IPetCategoryAnalyzer {

    @Override
    public void categoryChooser(Pets pets) {

        int cost = pets.getCost();

        if (cost >= 0 && cost <= 20)
            pets.setCategory(1);
        else if (cost <= 40)
           pets.setCategory(2);
        else if (cost <= 60)
            pets.setCategory(3);
        else
            pets.setCategory(4);
    }

    @Override
    public void pojoCategoryChooser(PetsPojo pets) {

        int cost = pets.getCost();

        if (cost >= 0 && cost <= 20)
            pets.setCategory(1);
        else if (cost <= 40)
            pets.setCategory(2);
        else if (cost <= 60)
            pets.setCategory(3);
        else
            pets.setCategory(4);
    }

}
