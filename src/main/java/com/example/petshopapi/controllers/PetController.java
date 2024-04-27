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
@RequestMapping("/pet-shop")
@AllArgsConstructor
public class PetController {

    private IPetsService service;

    @PostMapping("/add")
    ResponseEntity<String> add(@RequestBody Pets pets){
        service.add(pets);

        return new ResponseEntity<>("entity is added!", HttpStatus.CREATED);
    }

    @PostMapping("/upload/files")
    ResponseEntity<String> uploadFromFile(@RequestParam("file") MultipartFile multipartFile){
        service.uploadPetsFromFile(multipartFile);

        return new ResponseEntity<>("entities is added from file!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id){
        service.deleteById(id);

        return new ResponseEntity<>("entity is deleted!", HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    List<Pets> searchById(@PathVariable Long id){
        return service.searchById(id);
    }

    @GetMapping(("/all"))
    List<Pets> listAll(){
        return service.listAll();
    }

}
