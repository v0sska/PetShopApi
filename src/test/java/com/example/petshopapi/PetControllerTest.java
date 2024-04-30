package com.example.petshopapi;

import com.example.petshopapi.controllers.PetController;
import com.example.petshopapi.entities.Pets;
import com.example.petshopapi.interfaces.IPetsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetControllerTest {

    @InjectMocks
    private PetController petController;

    @Mock
    private IPetsService petsService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPetsTest(){

        Pets pets = new Pets();

        pets.setId(12L);
        pets.setName("test");
        pets.setType("test");
        pets.setSex("ts");
        pets.setWeight(13);
        pets.setCost(31);

        doNothing().when(petsService).addPet(any(Pets.class));

        ResponseEntity responseEntity = petController.addPet(pets);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("entity is added!", responseEntity.getBody());

        verify(petsService, times(1)).addPet(any(Pets.class));

    }

    @Test
    public void deletePetsTest(){

        Pets pets = new Pets();

        pets.setId(41L);
        pets.setName("test");
        pets.setType("test");
        pets.setSex("ts");
        pets.setWeight(13);
        pets.setCost(31);

        doNothing().when(petsService).deleteById(pets.getId());

        ResponseEntity responseEntity = petController.deleteById(pets.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("entity is deleted!", responseEntity.getBody());

        verify(petsService, times(1)).deleteById(pets.getId());

    }

    @Test
    public void findPetsByCriteriaTest(){

        List<Pets> expectedPetsList = new ArrayList<>();

        expectedPetsList.add(new Pets("testName1", "testType1", "testSex1", 21, 4, 6));
        expectedPetsList.add(new Pets("testName2", "testType3", "testSex4", 11, 2, 3));

        when(petsService.findPetsByCriteria(null, null, null, 21, null, null, null))
                .thenReturn(expectedPetsList);

        List<Pets> actualPetList = petController.findPetsByCriteria(null, null, null, null, 21, null, null);

        assertEquals(expectedPetsList, actualPetList);


    }


    @Test
    public void uploadFromXmlFileTest(){

        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<animals>\n" +
                "    <animal>\n" +
                "        <name>Cat</name>\n" +
                "        <type>Mammal</type>\n" +
                "        <sex>Female</sex>\n" +
                "        <weight>5</weight>\n" +
                "        <cost>100</cost>\n" +
                "    </animal>\n" +
                "</animals>";

        MultipartFile file = null;

        try {
            file = new MockMultipartFile("test.xml", new ByteArrayInputStream(xmlData.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        doNothing().when(petsService).uploadPetsFromFile(file);

        ResponseEntity responseEntity = petController.uploadFromFile(file);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("entities is added from file!", responseEntity.getBody());

        verify(petsService, times(1)).uploadPetsFromFile(file);

    }

    @Test
    public void uploadFromCsvFileTest(){

        MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/plain",
                ("name,type,sex,weight,cost\n" +
                        "Fluffy,Cat,F,5,100\n" +
                        "Buddy,Dog,M,10,200\n").getBytes());

        doNothing().when(petsService).uploadPetsFromFile(file);

        ResponseEntity responseEntity = petController.uploadFromFile(file);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("entities is added from file!", responseEntity.getBody());

        verify(petsService, times(1)).uploadPetsFromFile(file);

    }

}
