package com.example.petshopapi;

import com.example.petshopapi.components.PetCategoryAnalyzer;
import com.example.petshopapi.interfaces.ICsvParser;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.parsers.CsvParser;
import com.example.petshopapi.pojo.PetsPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CsvParserTest {

    private ICsvParser csvParser;
    private IPetCategoryAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = mock(PetCategoryAnalyzer.class);
        csvParser = new CsvParser(analyzer);
    }

    @Test
    void testLoadPetsFromCSV(){

        MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/plain",
                ("name,type,sex,weight,cost\n" +
                        "Fluffy,Cat,F,5,100\n" +
                        "Buddy,Dog,M,10,200\n").getBytes());



        PetsPojo cat = new PetsPojo();
        cat.setName("Fluffy");
        cat.setType("Cat");
        cat.setSex("F");
        cat.setWeight(5);
        cat.setCost(100);

        PetsPojo dog = new PetsPojo();
        dog.setName("Buddy");
        dog.setType("Dog");
        dog.setSex("M");
        dog.setWeight(10);
        dog.setCost(200);

        analyzer.pojoCategoryChooser(cat);
        analyzer.pojoCategoryChooser(dog);

        verify(analyzer, times(2)).pojoCategoryChooser(any());

        List<PetsPojo> pets = null;

        try {
            pets = csvParser.loadPetsFromCSV(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, pets.size());
        assertEquals(cat, pets.get(0));
        assertEquals(dog, pets.get(1));
    }

}
