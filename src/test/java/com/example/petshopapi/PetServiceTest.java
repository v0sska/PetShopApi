package com.example.petshopapi;

import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.ICsvParser;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.interfaces.IXmlParser;
import com.example.petshopapi.repositories.PetRepository;
import com.example.petshopapi.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository repository;

    @Mock
    private IPetCategoryAnalyzer analyzer;

    @Mock
    private ICsvParser csvParser;

    @Mock
    private IXmlParser xmlParser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPet() {
        Pets pet = new Pets("TestName", "TestType", "TestSex", 10, 50, 1);

        petService.addPet(pet);

        verify(analyzer, times(1)).categoryChooser(pet);
        verify(repository, times(1)).save(pet);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        petService.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testUploadPetsFromFileWithCsv(){
        MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/csv", "test data".getBytes());

        petService.uploadPetsFromFile(file);

        try {
            verify(csvParser, times(1)).loadPetsFromCSV(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    public void testUploadPetsFromFileWithXml(){
        MultipartFile file = new MockMultipartFile("test.xml", "test.xml", "application/xml", "test data".getBytes());

        petService.uploadPetsFromFile(file);

        try {
            verify(xmlParser, times(1)).readPetsFromXml(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    public void testFindPetsByCriteria() {
        List<Pets> expectedPets = Arrays.asList(
                new Pets("TestName1", "TestType1", "TestSex1", 10, 50, 1),
                new Pets("TestName2", "TestType2", "TestSex2", 20, 100, 2)
        );

        when(repository.findAll()).thenReturn(expectedPets);

        List<Pets> actualPets = petService.findPetsByCriteria(null, null, null, null, null, null, null);

        assertEquals(expectedPets.size(), actualPets.size());
        assertEquals(expectedPets, actualPets);
    }
}
