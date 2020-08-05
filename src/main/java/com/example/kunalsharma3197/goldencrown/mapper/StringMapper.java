package com.example.kunalsharma3197.goldencrown.mapper;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*
*  Class to convert the String input to a required ds list of pair of string

    getKingdomsAndMessages
*   e.g ------->

    String str =  "KINGDOM_1 SECRET_MSG_TO_KINGDOM_1"
       +"\n"
       + "KINGDOM_2 SECRET_MSG_TO_KINGDOM_2"

    After mapping the list generated will be ---->
    
    List<Pair<String, String>> kingdomsAndMessages = {
        {"KINGDOM_1", "SECRET_MSG_TO_KINGDOM_1"},
        {"KINGDOM_2", "SECRET_MSG_TO_KINGDOM_2"}
    }
    getKingdomsAndEmblem
    e.g -- > 
    String str =  "KINGDOM_1 emblem_1"
        +"\n"
       + "KINGDOM_2 emblem_2"
    Map<String, String> kingdomsAndEmblems = {
        "Kingdom1" : "Emblem1",
        "Kingdom2" : "Emblem2"
    }

 */
public class StringMapper implements ObjectMapper {
    
    public StringMapper() {
        // no args constructor for StringMapper.
    }

    /**
     * 
     * @param String data
     * @return String[]
     * splitDataByLineSeperator splits the given String data on the basis of empty line
     * and store each string after split in string array.
     */
    private String[] splitDataByLineSeperator(String data) {

        // returning the data as a String array in which each String was seperate by line seperator.
        return data.split(System.getProperty("line.separator"));
    }

    /**
     * 
     * @param String line
     * @return int index
     * getIndexOfFirstEmptyCharacter determines position of first empty character in a String line 
     */
    private int getIndexOfFirstEmptyCharacter(String line) {

        int index = 0; // index will contain the position of first empty character.

        // using while loop to reach the index where we encounter empty character for first time.
        while (line.charAt(index) != ' ') {
            ++index;
        } 
        return index;
    }

    /**
     * Method to perform mapping of data to List<Pair<String,String>>
     */
    public List<Pair<String, String>> getKingdomsAndMessages(String data) {

        // using LinkedList to achieve O(1) insertion 
        List<Pair<String, String>> kingdomsAndMessages = new LinkedList<>();

        //incase empty string is passed as data return empty list
        if (data == null || data.length() == 0) {
            return kingdomsAndMessages; 
        }
       
        String[] lines = splitDataByLineSeperator(data);

        for (String line : lines) {

            int index = getIndexOfFirstEmptyCharacter(line); // position of first empty character in line.
            
            // string to left of that index will be the name of kingdom and string to the right is the message.
            kingdomsAndMessages.add(new Pair<>(line
                .substring(0, index), line
                .substring(index + 1, line.length())));
        }
        
        return kingdomsAndMessages;
    }

    /**
     * Method to perform mapping of data to Map<String, String> kingdomAndEmblem which contains
     * name and emblem of each kingdom
     */
    public Map<String, String> getKingdomsAndEmblems(String data) {

        Map<String, String> kingdomsAndEmblems = new HashMap<>();// map to store kingdoms and their emblems.
        
        //incase their are no kingdoms return empty map.
        if (data == null || data.length() == 0) {
            return kingdomsAndEmblems;
        }

        // Storing the data as a String array.
        String[] lines = splitDataByLineSeperator(data);
        
        for (String line : lines) {

            int index = getIndexOfFirstEmptyCharacter(line); // position of first empty character in line.
            
            // string to left of that index will be the name of kingdom and string to the right is the emblem.
            String kingdom = line.substring(0, index);
            String emblem = line.substring(index + 1, line.length());
            kingdomsAndEmblems.put(kingdom, emblem);// adding key and value to the map.
        }
        return kingdomsAndEmblems;
    }

}