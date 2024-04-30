package com.example.petshopapi;

import com.example.petshopapi.components.PetCategoryAnalyzer;
import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.pojo.PetsPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PetCategoryAnalyzerTest {

    @Test
    public void categoryChooserTest(){

        IPetCategoryAnalyzer analyzer = new PetCategoryAnalyzer();
        Pets petsTest = new Pets();
        int expectedCategory = 2;

        petsTest.setType("test-type");
        petsTest.setCost(34);
        petsTest.setName("test-name");
        petsTest.setWeight(21);
        petsTest.setSex("test-sex");

        analyzer.categoryChooser(petsTest);

        int actualCategory = petsTest.getCategory();

        Assertions.assertNotNull(petsTest.getCategory());
        Assertions.assertEquals(expectedCategory, actualCategory);

    }

    @Test
    public void pojoCategoryChooserTest(){

        IPetCategoryAnalyzer analyzer = new PetCategoryAnalyzer();
        PetsPojo petsTest = new PetsPojo();
        int expectedCategory = 2;

        petsTest.setType("test-type");
        petsTest.setCost(34);
        petsTest.setName("test-name");
        petsTest.setWeight(21);
        petsTest.setSex("test-sex");

        analyzer.pojoCategoryChooser(petsTest);

        int actualCategory = petsTest.getCategory();

        Assertions.assertNotNull(petsTest.getCategory());
        Assertions.assertEquals(expectedCategory, actualCategory);

    }

}
