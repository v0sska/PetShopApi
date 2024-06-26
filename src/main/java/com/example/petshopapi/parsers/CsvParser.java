package com.example.petshopapi.parsers;

import com.example.petshopapi.interfaces.ICsvParser;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.pojo.PetsPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CsvParser implements ICsvParser {

    private IPetCategoryAnalyzer analyzer;


    @Override
    public List<PetsPojo> loadPetsFromCSV(MultipartFile file) throws IOException {
        List<PetsPojo> petsPojos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; //Skip first line
                }
                String[] data = line.split(",");

                boolean skipRow = false;
                for (String field : data) {
                    if (field.isEmpty()) {
                        skipRow = true;
                        break; // Skip object if at least one field is empty
                    }
                }
                if (skipRow) {
                    System.out.println("Skipped line: " + line);
                    continue; // Skip the line with empty field
                }

                if (data.length == 5) {
                    PetsPojo pet = new PetsPojo();

                    pet.setName(data[0].trim());
                    pet.setType(data[1].trim());
                    pet.setSex(data[2].trim());
                    pet.setWeight(Integer.parseInt(data[3].trim()));
                    pet.setCost(Integer.parseInt(data[4].trim()));

                    analyzer.pojoCategoryChooser(pet);

                    petsPojos.add(pet);
                } else
                    continue; //Skip fields which not valid to objects
            }
        }
        return petsPojos;
    }


}
