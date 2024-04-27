package com.example.petshopapi.services;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.interfaces.IPetsService;
import com.example.petshopapi.parsers.CsvParser;
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

    private CsvParser csvParser;

    @Override
    public void add(Pets pets) {

        analyzer.categoryChooser(pets);

        repository.save(pets);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Pets pets) {

    }

    @Override
    public List<Pets> searchById(Long id) {
        return repository.searchById(id);
    }

    @SneakyThrows
    @Override
    public void uploadPetsFromFile(MultipartFile fileToUpload) {

        List<PetsPojo> pojoList = csvParser.loadPetsFromCSV(fileToUpload);

        List<Pets> uploadedPets = pojoList.stream()
                .map(this::pojoToEntity)
                .collect(Collectors.toList());

        repository.saveAll(uploadedPets);
    }

    @Override
    public List<Pets> listAll() {
        return (List<Pets>) repository.findAll();
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
