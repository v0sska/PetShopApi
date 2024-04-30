package com.example.petshopapi.interfaces;

import com.example.petshopapi.entities.Pets;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPetsService {

    void addPet(Pets pets);

    void deleteById(Long id);

    void uploadPetsFromFile(MultipartFile fileToUpload);

    List<Pets> findPetsByCriteria(String name, String type, String sex, Integer weight, Integer cost, Integer category, Long id);
}
