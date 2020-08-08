package com.example.kunalsharma3197.goldencrown.solution;

import com.example.kunalsharma3197.goldencrown.decoder.MessageDecoder;
import com.example.kunalsharma3197.goldencrown.decoder.MessageDecoderImpl;
import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.*;
import java.util.stream.Collectors;


/**
 * RulerImpl implements Ruler class and performs the task of checking
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
public class RulerImpl implements Ruler {

    private Map<String, String> kingdomsAndEmblems;// Map to store Kingdoms and their emblem.
    final int minimumAlliesNeeded = 3; // minimum number of allies required to become the ruler.Can be changed as per requirement.
    final String king = "SPACE";// ruler kingdom
    final String noAllies = "NONE"; // represents that ruler kingdom has no allies.

    // Constructor of Ruler class to initialize
    // the map kingdomAndEmblem
    public RulerImpl(Map<String, String> kingdomsAndEmblems) {
        this.kingdomsAndEmblems = kingdomsAndEmblems;
    }

    /**
     * 
     * @param kingdomsAndMessages
     * @return Map<String,List<String>> ruler which contains
     *         ruler kingdom and its allies
     *         if number of allies are more than or equal to 3. 
     */
    @Override
    public Map<String, List<String>> getRulerAndAllies(List<Pair<String, String>> kingdomsAndMessages) {

        Map<String, List<String>> rulerAndAllies = new HashMap<>(); // map to store ruler as key and its allies as values.
        Set<String> uniqueKindoms = new HashSet<>(); // Set used to store unique Kingdoms to whome message was sent.

        rulerAndAllies.put(king, new LinkedList<>()); // Adding the ruler Kingdom as the key.

        /*
         * kingdomsAndMessages is a List of pair of strings where
         * first value in pair is the name of kingdom
         * second value in pair is the encoded message send by king shan to the kingdom
         * emblem is determined using map kingdomAndEmblem
         */
        for (Pair<String, String> kingdomAndMessage : kingdomsAndMessages) {

            String kingdom = kingdomAndMessage.first;
            String encodedMessage = kingdomAndMessage.second;
            String emblem = kingdomsAndEmblems.get(kingdom);

            // converting string emblem to upper case for ease of comparison with each char in decodedMessage.
            // and creating emblemAsCharList to store each character in emblem in a List of character.
            List<Character> emblemAsCharList = emblem.toUpperCase()
                    .chars()// Convert to an IntStream
                    .mapToObj(i -> (char) i)// Convert int to char, which gets boxed to Character
                    .collect(Collectors.toList());// Collect in a List<Character>

            // using getDecodedMessage to decode encoded message and store it in decodedMessage as List<Character>.
            List<Character> decodedMessage = getDecodedMessage(encodedMessage, emblem);

            // Map to store the character and frequency of character in decodedMessage.
            Map<Character, Integer> decodedCharAndFreqMap = generateCharAndFrequencyMap(decodedMessage);
            //Map to store character and frequency of character in emblem.
            Map<Character, Integer> emblemCharAndFreqMap = generateCharAndFrequencyMap(emblemAsCharList);

            /*
             * isAlliance is a function which we use to determine
             * whether a given kingdom will agree to become an
             * ally of Space kingdom
             */
            if (isAlliance(decodedCharAndFreqMap, emblemCharAndFreqMap) &&
                    !uniqueKindoms.contains(kingdom)) {

                // adding kingdom only if it is an ally of king shan. Hence we store only unique allies.
                uniqueKindoms.add(kingdom); 
                rulerAndAllies.get(king).add(kingdom);// adding that kingdom as an ally of king shan.
            }
        }

        /*
         *  we return the list of ruler and its allies only if
         *  number of allies are greater than or equal to 3.
         */
        if (uniqueKindoms.size() >= minimumAlliesNeeded) {
            return rulerAndAllies;                
        } 

        /*
         * We return Map<String,List<String>> containing king as key and None as value when it is not 
         * possible for king shan to rule all the kingdoms. None represent no ally kingdoms.
         */
        rulerAndAllies.put(king, Collections.singletonList(noAllies));
        return rulerAndAllies;
    }

    /**
     *
     * @param decodedCharAndFreqMap
     * @param emblemCharAndFreqMap
     * @return true if emblem characters are present in encoded message after decoding
     *         else false
     */
    private boolean isAlliance(Map<Character, Integer> decodedCharAndFreqMap,
                               Map<Character, Integer> emblemCharAndFreqMap) {


        for (char ch : emblemCharAndFreqMap.keySet()) { // for each character ch present as key in emblemCharAndFreqMap

            // if decodedCharFreqMap does not contain ch or the frequency of ch is less than
            // the frequency of ch in emblemCharAndFreqMap  we return false
            if (!decodedCharAndFreqMap.containsKey(ch) ||
                emblemCharAndFreqMap.get(ch) > decodedCharAndFreqMap.get(ch)) {
                    return false;
                }
        }
        //Every character from emblem was present and this kingdom will agree to become
        // an ally of King shan. Hence we return true.
        return true;
    }

    /**
     *
     * @param encodedMessage
     * @param emblem
     * @return decodedMessage
     * getDecodedMessage for given encodedMessage and emblem as String returns decodedMessage as a List of Character.
     * e.g -> encodedMessage = "ROZO"
     *        emblem = "OWL"
     *        decodedMessage = {'O','L','W','L'}
     */
    private List<Character> getDecodedMessage(String encodedMessage, String emblem) {

        MessageDecoder decoder = new MessageDecoderImpl();

        /*  Using decode method of MessageDecoder via its object decoder.
         *  the method takes two arguments : String encodedMessage, int secretKey
         *  emblem length is the secretKey.
         */
        int secretKey = emblem.length();
        List<Character> decodedMessage = decoder.decode(encodedMessage,
                secretKey);// Characters of encodedMessage is decoded and stored in decodedMessage.

        return decodedMessage; // returns the decodedMessage.
    }

    /**
     *
     * @param characterList
     * @return charAndFrequencyMap
     *
     * e.g -> characterList = {'A','B', 'A', 'C'}
     *        characterAndFrequencyMap = {
     *            'A' : 2,
     *            'B' : 1,
     *            'C' : 1
     *        }
     */
    private Map<Character, Integer> generateCharAndFrequencyMap(List<Character> characterList) {

        //charAndFrequencyMap stores each character from characterList along with its frequency.
        Map<Character, Integer> charAndFrequencyMap = new HashMap<>();

        for (Character character : characterList) {

            // putting each character character in characterList into decodedCharAndFrequencyMap along with its frequency.
            charAndFrequencyMap.put(character, charAndFrequencyMap.getOrDefault(character, 0) + 1);
        }

        return charAndFrequencyMap;
    }

}