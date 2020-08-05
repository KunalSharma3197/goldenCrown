package com.example.kunalsharma3197.goldencrown.mapper;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
*  Class to convert the String input to a list of pair of string

* e.g ------->

    String str =  "KINGDOM_1 SECRET_MSG_TO_KINGDOM_1"
       +"\n"
       + "KINGDOM_2 SECRET_MSG_TO_KINGDOM_2"

    After mapping the list generated will be ---->
    
    List<Pair<String, String>> kingdomsAndMessages = {
        {"KINGDOM_1", "SECRET_MSG_TO_KINGDOM_1"},
        {"KINGDOM_2", "SECRET_MSG_TO_KINGDOM_2"}
    }
    
 */
public class StringMapperTest {

    /**
     * these tests checks if the getKingdomsAndMessages of StringMapper is able to map the given input 
     * to the expected data structure
     */
    private ObjectMapper stringMapper;

    @BeforeEach 
    void init() {
        stringMapper = new StringMapper();// initialising new instance of StringMapper before each test.
    }
    @Test
    public void getKingdomAndMessagesTest() {

        //data contains the kingdoms and messages sent to them
        String data = "AIR ROZO" 
            + System.lineSeparator() 
            + "LAND FAIJWJSOOFAMAU"
            + System.lineSeparator()
            + "ICE STHSTSTVSASOS";
        

        //actual contains the result of mapping done by StringMapper
        List<Pair<String, String>> actual = stringMapper.getKingdomsAndMessages(data);

        //expected contains the correct result of mapping. The contents of actual must be equal to expected.
        List<Pair<String, String>> expected = Arrays
            .asList(new Pair<String, String>("AIR", "ROZO"),
            new Pair<String, String>("LAND", "FAIJWJSOOFAMAU"), 
            new Pair<String, String>("ICE", "STHSTSTVSASOS"));

        assertEquals(3, actual.size());
        assertTrue(expected.equals(actual));
    }

    /**
     * this test is designed for testing the functionality of getKingdomsAndMessages of StringMapper
     * in case message is seperated by spaces.
     */
    @Test
    public void messagesSeperatedBySpaceTest() {

        //data contains the kingdoms and messages sent to them
        String data = "AIR ROZO IS IN THE AIR" // messages contains spaces
            + System.lineSeparator() 
            + "WATER SUMMER IS COMING";
        
        //actual contains the result of mapping done by StringMapper
        List<Pair<String, String>> actual = stringMapper.getKingdomsAndMessages(data);

        
        String expectedMessageToAirKingdom = "ROZO IS IN THE AIR";// expected message to AIR kingdom.
        String expectedMessageToWaterKingdom = "SUMMER IS COMING";// expected message to WATER kingdom.

        String actualMessageToAirKingdom = actual.get(0).second; // actual message to AIR kingdom.
        String actualMessageToWaterKingdom = actual.get(1).second;// actual message to WATER kingdom.

        assertEquals(expectedMessageToAirKingdom, actualMessageToAirKingdom);
        assertEquals(expectedMessageToWaterKingdom, actualMessageToWaterKingdom);
    }
    /**
     * this test is designed for testing of getKingdomsAndMessages of StringMapper 
     * in case empty String is passed as data.
     */
    @Test 
    public void emptyStringPassedAsDataToGetKingdomAndMessagesTest() {

        String data = ""; //empty data.

        //actual contains the result of mapping done by StringMapper
        List<Pair<String, String>> actual = stringMapper.getKingdomsAndMessages(data);

        assertEquals(0, actual.size());
    }

    /**
     * this test the functionality of getKingdomsAndEmblem method of StringMapper for mapping data
     * of kingdoms and emblems to a Map<String, String> containing kingdoms and their emblems.
     */

    @Test
    public void getKingdomsAndEmblemTest() {
        //data contains the kingdoms and their emblems.
        String data = "AIR Owl" 
            + System.lineSeparator() 
            + "LAND Panda"
            + System.lineSeparator()
            + "ICE Mammoth"
            + System.lineSeparator()
            + "Space Gorilla";

        //actual contains the result of mapping done by StringMapper
        Map<String, String> actual = stringMapper.getKingdomsAndEmblems(data);

        //expected contains the correct result of mapping. The contents of actual must be equal to expected.
        Map<String, String> expected = new HashMap<>();
        expected.put("AIR", "Owl");
        expected.put("LAND", "Panda");
        expected.put("ICE" ,"Mammoth");
        expected.put("Space" ,"Gorilla");

        assertEquals(expected, actual);
        
    }

    /**
     * this test is designed for testing of getKingomsAndEmblemsStringMapper 
     * in case empty String is passed as data.
     */
    @Test 
    public void emptyStringPassedAsDataToGetKingdomAndEmblemTest() {
        String data = ""; 
        //actual contains the result of mapping done by StringMapper
        Map<String, String> actual = stringMapper.getKingdomsAndEmblems(data);

        assertEquals(0, actual.size());
    }
}