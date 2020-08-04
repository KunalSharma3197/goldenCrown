package com.example.kunalsharma3197.goldencrown.solution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import org.junit.jupiter.api.Test;

/**
* This class checks the decoding finctionality of ruler class.
 */

public class RulerTest{
 
    /**
     * This test is designed for testing the functionality in case no message was sent by 
     * ruler kingdom.
     */
    @Test
    public void noMessagesSentByRulerKingdomTest() {
        Ruler ruler = new Ruler();
        List<Pair<String, String>> kingdomsAndMessages = new LinkedList<>();
        String king  = "Space";

        //passing an empty list as argument to getRuler
        Map<String, List<String>> rulerAndAllies = ruler.getRulerAndAllies(kingdomsAndMessages);
        //expected output is None
        assertEquals("None", rulerAndAllies.get(king).get(0));
    }

    /**
     * This test is designed for testing the functionality in case all message are sent to 
     * the same kingdom.
     */
    @Test 
    public void allMessagesSentToTheSameKingdomTest() {
        Ruler ruler = new Ruler();
        List<Pair<String, String>> kingdomsAndMessages = new LinkedList<>();
        String king  = "Space";
        //passing same message multiple time to same kingdom
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));

        Map<String, List<String>> rulerAndAllies = ruler
            .getRulerAndAllies(kingdomsAndMessages);//expected output is None
        assertEquals(Arrays.asList("None"), rulerAndAllies.get(king));
    }

    /**
     * This test is designed for testing the functionality of getRulerAndAllies method of
     * Ruler class
     */
    @Test
    public void getRulerAndAlliesTest() {

        Ruler ruler = new Ruler();
        List<Pair<String, String>> kingdomsAndMessages = new LinkedList<>();
        String king  = "Space";

        //adding kingdoms and messages sent into the list
        kingdomsAndMessages.add(new Pair<>("AIR", "ROZO"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("ICE", "STHSTSTVSASOS"));

        // all the above messeges after decode contains the character present in the
        // emblem of their kingdoms. Hence all these kingdom will agree to become the ally of King shan

        Map<String, List<String>> rulerAndAllies = ruler
            .getRulerAndAllies(kingdomsAndMessages);

        assertEquals(3, rulerAndAllies.get(king).size());

        assertEquals(Arrays.asList("AIR", "LAND", "ICE"),
            rulerAndAllies.get(king));
        
    }
}