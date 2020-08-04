package com.example.kunalsharma3197.goldencrown.mapper;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.LinkedList;
import java.util.List;


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
public class StringMapper implements ObjectMapper {
    
    private String data;//class attribute to store data parsed from .txt file 
    
    public StringMapper(String data) {

        this.data = data;
    }

    /**
     * Method to perform mapping of data to List<Pair<String,String>>
     */
    public List<Pair<String, String>> getKingdomsAndMessages() {

        // using LinkedList to achieve O(1) insertion 
        List<Pair<String, String>> kingdomsAndMessages = new LinkedList<>();

        //incase empty string is passed as data return empty list
        if (data == null || data.length() == 0) {
            return kingdomsAndMessages; 
        }
       
        // Storing the data as a String array.
        String[] lines = data.split(System.getProperty("line.separator"));

        for (String line : lines) {

            int index = 0;

            // using while loop to reach the index where we encounter empty character for first time.
            while (line.charAt(index) != ' ') {
                ++index;
            } 

            // string to left of that index will be the name of kingdom and string to the right is the message.
            kingdomsAndMessages.add(new Pair<>(line
                .substring(0, index), line
                .substring(index + 1, line.length())));
        }
        
        return kingdomsAndMessages;
    }
}