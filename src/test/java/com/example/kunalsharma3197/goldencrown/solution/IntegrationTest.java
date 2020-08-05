package com.example.kunalsharma3197.goldencrown.solution;

import static org.junit.Assert.assertEquals;

import com.example.kunalsharma3197.goldencrown.mapper.ObjectMapper;
import com.example.kunalsharma3197.goldencrown.mapper.StringMapper;
import com.example.kunalsharma3197.goldencrown.pair.Pair;
import com.example.kunalsharma3197.goldencrown.parser.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class IntegrationTest {
    /**
     * This test is an integration test and checks how all the units defined
     * in the project perform together.
     */ 
    private FileParser fileParser; // FileParser object
    private ObjectMapper objectMapper; //ObjectMapper object
    static final String pathToKingdomsAndMessagesFile = "src/test/resources/KingdomsAndMessages.txt";
    static final String pathToKingdomsAndEmblemsFile = "src/test/resources/KingdomsAndEmblems.txt";
    static final String king = "Space";

    @BeforeEach
    public void init() {
        fileParser = new FileParser();// initialising new instance of FileParser before every test.
    }


    String getContentsOfFile(String pathToFile) throws IOException {

        File file = new File(pathToFile); // create a new File for the specified path
        fileParser = new FileParser(); // Creating new instance of FileParser.

        //parsing and returning contents of the file as string using absolute path for specified file.
        return fileParser.getContentsOfFile(file.getAbsolutePath());
    }

    /**
     * following test checks the output produced after integrating all the units of the program 
     * to see how various perform on integration. 
     * 
     * @throws IOException
     */
    @Test
    public void testingOutputlProducedByIntegratedUnits() throws IOException {

        // kingdomsAndMessagesData contains the content of input file as string
        String kingdomsAndMessagesData = getContentsOfFile(pathToKingdomsAndMessagesFile);

        // kingdomsAndEmblemsData contains contents of KingdomsAndEmblems.txt as String
        String kingdomsAndEmblemsData = getContentsOfFile(pathToKingdomsAndEmblemsFile);

        objectMapper = new StringMapper();// initialising new instance of StringMapper before every test.

        //we used objectMapper to map the kingdomsAndEmblemsData to kingdomsAndEmblems. 
        Map<String, String> kingdomsAndEmblems = objectMapper
            .getKingdomsAndEmblems(kingdomsAndEmblemsData);
        
        // kingdomsAndMessages contains the content of kingdomsAndMessagesData mapped using objectMapper
        List<Pair<String, String>> kingdomsAndMessages = objectMapper
            .getKingdomsAndMessages(kingdomsAndMessagesData);

        // Creating new Instance of Ruler using map of kingdoms and emblems
        Ruler ruler = new Ruler(kingdomsAndEmblems);

        // Determining the required ruler and list of allies using ruler.getRulerAndAllies()
        // List<Pair<String, String>> mapped using objectMapper is passed as argument
        Map<String,List<String>> rulerAndAllies = ruler.getRulerAndAllies(kingdomsAndMessages);
        
        //expected contains the correct expected result. rulerAndAllies must have 
        //content similar to expected to pass the test.
        Map<String, List<String>> expected = new HashMap<>();
        expected.put(king, new LinkedList<String>());
        expected.get(king).add("AIR");
        expected.get(king).add("LAND");
        expected.get(king).add("ICE");

        assertEquals(expected, rulerAndAllies);
    }

}
