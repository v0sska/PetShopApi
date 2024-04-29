package com.example.petshopapi.interfaces;

import com.example.petshopapi.pojo.PetsPojo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IXmlParser {

    List<PetsPojo> readPetsFromXml(MultipartFile fileToRead) throws IOException;

}
