package com.example.kunalsharma3197.goldencrown.mapper;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
     * this test checks if the StringMapper is able to map the given input 
     * to the expected data structure
     */
    private StringMapper stringMapper;
    @Test
    public void getKingdomAndMessagesTest() {
        String data = "AIR ROZO";
        stringMapper = new StringMapper(data);
        List<Pair<String, String>> actual = stringMapper.getKingdomsAndMessages();
        assertEquals(1, actual.size());
        assertEquals("AIR", actual.get(0).first);
    }

    /**
     * this test is designed for testing of StringMapper in case empty String is passed as data.
     */
    @Test 
    public void emptyStringPassedAsDataTest() {
        String data = ""; 
        stringMapper = new StringMapper(data);
        List<Pair<String, String>> actual = stringMapper.getKingdomsAndMessages();

        assertEquals(0, actual.size());
    }
}