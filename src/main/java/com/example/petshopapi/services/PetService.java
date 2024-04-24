package com.example.petshopapi.services;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetsService;
import com.example.petshopapi.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService implements IPetsService {

    private PetRepository repository;

    @Override
    public void add(Pets pets) {
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

    @Override
    public List<Pets> listAll() {
        return null;
    }
}
