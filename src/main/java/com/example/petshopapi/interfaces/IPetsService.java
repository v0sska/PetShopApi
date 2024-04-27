package com.example.petshopapi.interfaces;

import com.example.petshopapi.entities.Pets;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPetsService {

    void add(Pets pets);

    void deleteById(Long id);

    void updateById(Long id, Pets pets);

    List<Pets> searchById(Long id);

    void uploadPetsFromFileCSV(MultipartFile fileToUpload);

    void uploadPetsFromFileXml(MultipartFile uploadFile);

    List<Pets> listAll();
}
