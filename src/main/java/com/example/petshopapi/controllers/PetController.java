package com.example.petshopapi.controllers;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pet-data")
@AllArgsConstructor
public class PetController {

    private IPetsService service;

    @PostMapping("/add")
   public ResponseEntity<String> addPet(@RequestBody Pets pets){
        service.addPet(pets);

        return new ResponseEntity<>("entity is added!", HttpStatus.CREATED);
    }

    @PostMapping("/upload/files")
    public ResponseEntity<String> uploadFromFile(@RequestParam("file") MultipartFile multipartFile){
        service.uploadPetsFromFile(multipartFile);

        return new ResponseEntity<>("entities is added from file!", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        service.deleteById(id);

        return new ResponseEntity<>("entity is deleted!", HttpStatus.OK);
    }
    @GetMapping("/find")
    public List<Pets> findPetsByCriteria(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) Integer weight,
            @RequestParam(required = false) Integer cost,
            @RequestParam(required = false) Integer category){

        return service.findPetsByCriteria(name, type, sex, weight, cost, category, id);

    }

}
