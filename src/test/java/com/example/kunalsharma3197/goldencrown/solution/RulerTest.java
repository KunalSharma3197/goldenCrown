package com.example.kunalsharma3197.goldencrown.solution;

import static org.junit.Assert.assertEquals;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
* These tests checks the finctionality of ruler class under various scenarios.
 */
@ExtendWith(Mockito)
public class RulerTest{

    Map<String, String> kingdomsAndEmblems;
    String king;
    List<Pair<String, String>> kingdomsAndMessages;
    // @Mock
    // private MessageDecoderImpl decoder;
    /**
     * initialise the kingdom and emblem map before each test.
     */
    @BeforeEach
    void init() {
        kingdomsAndEmblems = new HashMap<>();
        kingdomsAndEmblems.put("SPACE", "Gorilla");
        kingdomsAndEmblems.put("LAND", "Panda");
        kingdomsAndEmblems.put("WATER", "Octopus");
        kingdomsAndEmblems.put("ICE", "Mammoth");
        kingdomsAndEmblems.put("AIR", "Owl");
        kingdomsAndEmblems.put("FIRE", "Dragon");
        king = "SPACE";

        // initialising a new instance of kingdomsAndMessages before each test.
        kingdomsAndMessages = new LinkedList<>();
    }


    /**
     * This test is designed for testing the functionality in case no message was sent by 
     * ruler kingdom.
     */
    
    @Test
    public void noMessagesSentByRulerKingdomTest() {
        Ruler ruler = new Ruler(kingdomsAndEmblems);

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

        Ruler ruler = new Ruler(kingdomsAndEmblems);

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

        Ruler ruler = new Ruler(kingdomsAndEmblems);

        //adding kingdoms and messages sent into the list kingdomsAndMessages.
        kingdomsAndMessages.add(new Pair<>("AIR", "ROZO"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("ICE", "STHSTSTVSASOS"));

        // all the above messages after decode contains the character present in the
        // emblem of their kingdoms. Hence all these kingdom will agree to become the ally of King shan

        Map<String, List<String>> rulerAndAllies = ruler
            .getRulerAndAllies(kingdomsAndMessages);

        assertEquals(3, rulerAndAllies.get(king).size());

        assertEquals(Arrays.asList("AIR", "LAND", "ICE"),
            rulerAndAllies.get(king));
        
    }

    /**
     * this test tests the functionality of getRulerAndAllies in case a new Kingdom is added among
     * the existing kingdoms
     */
    
    @Test
    public void getRulerAndAlliesAfterANewKingdomIsAddedTest() {

        //we will consider Blaze as the new Kingdom and its emblem is Phoenix
        kingdomsAndEmblems.put("BLAZE", "Phoenix");

        Ruler ruler = new Ruler(kingdomsAndEmblems);

        //adding kingdoms and messages sent into the list kingdomsAndMessages.
        kingdomsAndMessages.add(new Pair<>("AIR", "ROZO"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("ICE", "STHSTSTVSASOS"));
        kingdomsAndMessages.add(new Pair<>("BLAZE", "ABWSODVLKUPLE"));
        kingdomsAndMessages.add(new Pair<>("FIRE", "QLMPODTY"));

        // Four of  the above messages after decode contains the character present in the
        // emblem of their kingdoms. Hence those kingdom will agree to become the ally of King shan
        // rulerAndAllies contains the ruler kingdom along with allies.
        Map<String, List<String>> rulerAndAllies = ruler
            .getRulerAndAllies(kingdomsAndMessages);
        
        //expected contains the correct expected result. rulerAndAllies must have 
        //content similar to expected to pass the test.
        Map<String, List<String>> expected = new HashMap<>();
        expected.put(king, new LinkedList<String>());
        expected.get(king).add("AIR");
        expected.get(king).add("LAND");
        expected.get(king).add("ICE");
        expected.get(king).add("BLAZE");

        assertEquals(expected, rulerAndAllies);
    }
}