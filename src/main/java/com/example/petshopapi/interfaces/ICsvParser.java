package com.example.petshopapi.interfaces;

import com.example.petshopapi.pojo.PetsPojo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICsvParser {
    List<PetsPojo> loadPetsFromCSV(MultipartFile file) throws IOException;

}
