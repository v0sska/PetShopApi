package com.example.petshopapi;

import com.example.petshopapi.components.PetCategoryAnalyzer;
import com.example.petshopapi.interfaces.IPetCategoryAnalyzer;
import com.example.petshopapi.interfaces.IXmlParser;
import com.example.petshopapi.parsers.XmlParser;
import com.example.petshopapi.pojo.PetsPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class XmlParserTest {

    private IPetCategoryAnalyzer analyzer;

    private IXmlParser xmlParser;

    @BeforeEach
    void setUp() {
        analyzer = mock(PetCategoryAnalyzer.class);
        xmlParser = new XmlParser(analyzer);
    }

    @Test
    void readPetsFromXmlTest() {

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

        PetsPojo expectedPojo = new PetsPojo();
        expectedPojo.setName("Cat");
        expectedPojo.setType("Mammal");
        expectedPojo.setSex("Female");
        expectedPojo.setWeight(5);
        expectedPojo.setCost(100);

        analyzer.pojoCategoryChooser(expectedPojo);

        verify(analyzer, times(1)).pojoCategoryChooser(any());

        List<PetsPojo> result = null;

        try {
            result = xmlParser.readPetsFromXml(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.asList(expectedPojo), result);
    }
}
