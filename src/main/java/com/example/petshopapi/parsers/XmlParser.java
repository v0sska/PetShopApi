package com.example.petshopapi.parsers;

import com.example.petshopapi.components.PetCategoryAnalyzer;
import com.example.petshopapi.pojo.PetsPojo;
import com.example.petshopapi.xml_objects.Animal;
import com.example.petshopapi.xml_objects.Animals;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class XmlParser {

    PetCategoryAnalyzer analyzer;

   public List<PetsPojo> readPetsFromXml(MultipartFile fileToRead) throws IOException {

        File tempFile = File.createTempFile("temp", null);

        // Запис даних з MultipartFile в тимчасовий файл
        try (OutputStream os = new FileOutputStream(tempFile)) {
            FileCopyUtils.copy(fileToRead.getInputStream(), os);
        }

        List<PetsPojo> readedPojos = new ArrayList<>();

        XmlMapper xmlMapper = new XmlMapper();
        Animals animals = xmlMapper.readValue(tempFile, Animals.class);

        for (Animal animal : animals.getAnimals()) {
            PetsPojo pojo = new PetsPojo();

            pojo.setName(animal.getName());
            pojo.setType(animal.getType());
            pojo.setSex(animal.getSex());
            pojo.setWeight(animal.getWeight());
            pojo.setCost(animal.getCost());

            analyzer.pojoCategoryChooser(pojo);

            readedPojos.add(pojo);

        }

        tempFile.delete();

        return readedPojos;
    }

}
