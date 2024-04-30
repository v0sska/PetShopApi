package com.example.petshopapi.services;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.ICsvParser;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.interfaces.IPetsService;
import com.example.petshopapi.interfaces.IXmlParser;
import com.example.petshopapi.pojo.PetsPojo;
import com.example.petshopapi.repositories.PetRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService implements IPetsService {

    private PetRepository repository;

    private IPetCategoryAnalyzer analyzer;

    private ICsvParser csvParser;

    private IXmlParser xmlParser;

    @Override
    public void addPet(Pets pets) {

        analyzer.categoryChooser(pets);

        repository.save(pets);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public void uploadPetsFromFile(MultipartFile fileToUpload) {
        String fileName = fileToUpload.getOriginalFilename();

        List<PetsPojo> petsPojos;

        if(fileName != null && (fileName.endsWith(".csv") || fileName.endsWith(".CSV")))
            petsPojos = csvParser.loadPetsFromCSV(fileToUpload);
        else if(fileName != null && (fileName.endsWith(".xml") || fileName.endsWith(".XML")))
            petsPojos = xmlParser.readPetsFromXml(fileToUpload);
        else
            throw new IllegalArgumentException("Unsupported format!");

        List<Pets> uploadedPets = petsPojos.stream()
                .map(this::pojoToEntity)
                .collect(Collectors.toList());

        repository.saveAll(uploadedPets);
    }

    @Override
    public List<Pets> findPetsByCriteria(String name, String type, String sex, Integer weight, Integer cost, Integer category, Long id) {
        List<Pets> filter;

        if(type != null && sex != null && category != -1)
            filter = repository.searchByTypeAndCategoryAndSex(type, category, sex);
        else if(type != null)
            filter = repository.searchByType(type);
        else if(name != null)
            filter = repository.searchByName(name);
        else if(sex != null)
            filter = repository.searchBySex(sex);
        else if(weight != null)
            filter = repository.searchByWeight(weight);
        else if(cost != null)
            filter = repository.searchByCost(cost);
        else if(category != null)
            filter = repository.searchByCategory(category);
        else if(id != null)
            filter = repository.searchById(id);
        else
            filter = (List<Pets>) repository.findAll();
        return filter;
    }

    private Pets pojoToEntity(PetsPojo petsPojo){
        Pets pets = new Pets();

        pets.setName(petsPojo.getName());
        pets.setType(petsPojo.getType());
        pets.setSex(petsPojo.getSex());
        pets.setWeight(petsPojo.getWeight());
        pets.setCost(petsPojo.getCost());
        pets.setCategory(petsPojo.getCategory());

        return pets;
    }
}
