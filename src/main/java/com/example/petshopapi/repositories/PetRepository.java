package com.example.petshopapi.repositories;

import com.example.petshopapi.entities.Pets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pets, Long> {
    List<Pets> searchById(Long id);

    List<Pets> searchByCategory(int category);

    List<Pets> searchByType(String type);

    List<Pets> searchByName(String name);

    List<Pets> searchBySex(String sex);

    List<Pets> searchByWeight(int weight);

    List<Pets> searchByCost(int cost);

    List<Pets> searchByTypeAndCategoryAndSex(String type, int category, String sex);
}
