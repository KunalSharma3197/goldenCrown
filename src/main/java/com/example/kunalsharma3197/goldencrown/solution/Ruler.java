package com.example.kunalsharma3197.goldencrown.solution;

import com.example.kunalsharma3197.goldencrown.decoder.MessageDecoder;
import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ruler class performs the task of checking
 * if it is possible to become a ruler of all kingdoms if yes we return list of ruler
 * and ally kingdoms.
 * If not possible we return list with String none.
 * e.g 
 *      List<Pair<String, String>> kingdomsAndMessages = {
                {"AIR", "ROZO"},
                {"LAND", "FAIJWJSOOFAMAU"},
                {"ICE", "STHSTSTVSASOS"}
            }
        Map<String, List<String> >ruler = {
                "Space" : {"AIR", "LAND", "ICE"}
            }
 */

public class Ruler {

    private Map<String, String> kingdomsAndEmblems;// Map to store Kingdoms and their emblem.
    static final int mininmuAlliesNeeded = 3; // minimum number of allies required to become the ruler.
    static final String king = "Space";// ruler kingdom

    // Constructor of Ruler class to intialise 
    // the map kingdomAndEmblem
    public Ruler(Map<String, String> kingdomsAndEmblems) {
        this.kingdomsAndEmblems = kingdomsAndEmblems;
    }

    /**
     * 
     * @param kingdomsAndMessages
     * @return Map<String,List<String>> ruler which contains
     *         ruler kingdom and its allies
     *         if number of allies are more than or equal to 3. 
     */
    public Map<String, List<String>> getRulerAndAllies(List<Pair<String, String>> kingdomsAndMessages) {

        Map<String, List<String>> rulerAndAllies = new HashMap<>(); // map to store ruler as key and its allies as values.
        Set<String> uniqueKindoms = new HashSet<>(); // Set used to store unique Kingdoms to whome message was sent.

        rulerAndAllies.put(king, new LinkedList<String>()); // Adding the ruler Kingdom as the key.

        /**
         * kingdomsAndMessages is a List of pair of strings where
         * first value in pair is the name of kingdom
         * second value in pair is the encoded message send by king shan to the kingdom
         * emblem is determined using map kingdomAndEmblem
         */
        for (Pair<String, String> kingdomAndMessage : kingdomsAndMessages) {

            String kingdom = kingdomAndMessage.first;
            String encodedMessage = kingdomAndMessage.second;
            String emblem = kingdomsAndEmblems.get(kingdom);

            /**
             * isAlliance is a function which we use to determine 
             * whether a given kingdom will agree to become an
             * ally of Space kingdom
             */
            if (isAlliance(encodedMessage, emblem) &&
                uniqueKindoms.contains(kingdom) == false) {

                // adding kingdom only if it is an ally of king shan. Hence we store only unique allies.
                uniqueKindoms.add(kingdom); 
                rulerAndAllies.get(king).add(kingdom);// adding that kingdom as an ally of king shan.
            }
        }

        /**
         *  we return the list of ruler and its allies only if
         *  numnber of allies are greater than or equal to 3.
         */
        if (uniqueKindoms.size() >= mininmuAlliesNeeded) {
            return rulerAndAllies;                
        } 

        /**
         * We return Map<String,List<String>> containing king as key and None as value when it is not 
         * possible for king shan to rule all the kingdoms. None represent no ally kingdoms.
         */
        rulerAndAllies.put(king, Arrays.asList("None"));
        return rulerAndAllies;
    }

    /**
     * 
     * @param encodedMessage -> message sent by King shan to a the current kingdom
     * @param emblem  -> emblem of the current kingdom
     * @return true if emblem characters are present in encoded message after decoding
     *         else false
     * 
     * e.g encodedMessage = "ROZO"
     *     emblem = "OWL"
     */
    private boolean isAlliance(String encodedMessage, String emblem) {

        MessageDecoder decoder = new MessageDecoder();

        /*  Using decode method of MessageDecoder via its object decoder.
         *  the method takes two arguments : String encodedMessage, int secretKey 
         *  emblem length is the secretKey. 
         */
        int secretKey = emblem.length();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 
            secretKey);

        //  converting string emblem to upper case for ease of comparison with each char in decodedMessage.
        char[] emblemArray = emblem.toUpperCase().toCharArray();

        // Map to store the character and frequency of character in decodedMessage.
        Map<Character, Integer> decodedCharAndFreqMap = new HashMap<>();

        //Map to store character and frequency of character in emblem.
        Map<Character, Integer> emblemCharAndFreqMap = new HashMap<>();

        for (char ch : decodedMessage) {

            // putting each character ch in decodedMessage into decodedCharAndFreqmap along with its frequency.
            decodedCharAndFreqMap.put(ch, decodedCharAndFreqMap.getOrDefault(ch, 0) + 1);
        } 

        for (char ch : emblemArray) {

            // putting each character ch in emblem into encodedCharAndFreqmap along with its frequency.
            emblemCharAndFreqMap.put(ch, emblemCharAndFreqMap.getOrDefault(ch, 0) + 1);
        } 

        for (char ch : emblemCharAndFreqMap.keySet()) { // for each character ch present as key in emblemCharAndFreqMap

            // if decodedCharFreqMap does not contain ch or the frequency of ch is less than
            // the frequency of ch in emblemCharAndFreqMap  we return false
            if (decodedCharAndFreqMap.containsKey(ch) == false ||
                emblemCharAndFreqMap.get(ch) > decodedCharAndFreqMap.get(ch)) {
                    return false;
                }
        }
        //Every character from emblem was present and this kingdom will agree to become
        // an ally of King shan. Hence we return true.
        return true;
    }

}