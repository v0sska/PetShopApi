package com.example.petshopapi.interfaces;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.pojo.PetsPojo;

public interface IPetCategoryAnalyzer {

    public void categoryChooser(Pets pets);

    public void pojoCategoryChooser(PetsPojo pets);

}
