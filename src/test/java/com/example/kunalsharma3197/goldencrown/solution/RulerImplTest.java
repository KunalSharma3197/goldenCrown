package com.example.kunalsharma3197.goldencrown.solution;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.kunalsharma3197.goldencrown.decoder.MessageDecoderImpl;
import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/**
* These tests checks the functionality of ruler class under various scenarios.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RulerImplTest {

    private Map<String, String> kingdomsAndEmblems;
    private String king;
    private List<Pair<String, String>> kingdomsAndMessages;
    @Mock
    private MessageDecoderImpl decoder = new MessageDecoderImpl();

    @InjectMocks
    private RulerImpl ruler;

    /**
     * initialise the kingdom and emblem map before each test.
     */
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        kingdomsAndEmblems = new HashMap<>();
        kingdomsAndEmblems.put("SPACE", "Gorilla");
        kingdomsAndEmblems.put("LAND", "Panda");
        kingdomsAndEmblems.put("WATER", "Octopus");
        kingdomsAndEmblems.put("ICE", "Mammoth");
        kingdomsAndEmblems.put("AIR", "Owl");
        kingdomsAndEmblems.put("FIRE", "Dragon");
        king = "SPACE";

        kingdomsAndMessages = new LinkedList<>();
        ruler  = new RulerImpl(kingdomsAndEmblems);

    }


    /**
     * This test is designed for testing the functionality in case no message was sent by 
     * ruler kingdom.
     */
    
    @Test
    public void noMessagesSentByRulerKingdomTest() {
        List<Character> decodedMessage = new LinkedList<>();
        when(decoder.decode(anyString(), anyInt())).thenReturn(decodedMessage);

        //passing an empty list as argument to getRuler
        Map<String, List<String>> rulerAndAllies = ruler.getRulerAndAllies(kingdomsAndMessages);
        //expected output is None
        assertEquals("NONE", rulerAndAllies.get(king).get(0));
    }

    /**
     * This test is designed for testing the functionality in case all message are sent to 
     * the same kingdom.
     */
    @Test 
    public void allMessagesSentToTheSameKingdomTest() {

        List<Character> decodedMessage = Arrays.asList('A', 'V', 'D', 'E', 'R', 'E', 'N',
                'J', 'J', 'A', 'V', 'H', 'V', 'P');
        when(decoder.decode("FAIJWJSOOFAMAU", 5)).thenReturn(decodedMessage);

        //passing same message multiple time to same kingdom
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));
        kingdomsAndMessages.add(new Pair<>("LAND", "FAIJWJSOOFAMAU"));

        Map<String, List<String>> rulerAndAllies = ruler
            .getRulerAndAllies(kingdomsAndMessages);//expected output is NONE
        assertEquals(Arrays.asList("NONE"), rulerAndAllies.get(king));
    }

    /**
     * This test is designed for testing the functionality of getRulerAndAllies method of
     * Ruler class
     */
    @Test
    public void getRulerAndAlliesTest() {
        List<Character> decodedMessage1 = Arrays.asList('O', 'L', 'W', 'L');

        List<Character> decodedMessage2 = Arrays.asList('A', 'V', 'D', 'E', 'R', 'E', 'N',
                'J', 'J', 'A', 'V', 'H', 'V', 'P');

        List<Character> decodedMessage3 = Arrays.asList('L', 'M', 'A', 'L', 'M', 'L', 'M',
                'O', 'L', 'T', 'L', 'H', 'L');


        when(decoder.decode("ROZO", 3)).thenReturn(decodedMessage1);
        when(decoder.decode("FAIJWJSOOFAMAU", 5)).thenReturn(decodedMessage2);
        when(decoder.decode("STHSTSTVSASOS", 7)).thenReturn(decodedMessage3);

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

        List<Character> decodedMessage1 = Arrays.asList('O', 'L', 'W', 'L');

        List<Character> decodedMessage2 = Arrays.asList('A', 'V', 'D', 'E', 'R', 'E', 'N',
                'J', 'J', 'A', 'V', 'H', 'V', 'P');

        List<Character> decodedMessage3 = Arrays.asList('L', 'M', 'A', 'L', 'M', 'L', 'M',
                'O', 'L', 'T', 'L', 'H', 'L');

        List<Character> decodedMessage4 = Arrays.asList('T', 'U', 'P', 'L', 'H', 'W', 'O',
                'E', 'D', 'N', 'I', 'E', 'X');

        List<Character> decodedMessage5 = Arrays.asList('K', 'F', 'G', 'J', 'I', 'X', 'N', 'S');

        when(decoder.decode("ROZO", 3)).thenReturn(decodedMessage1);
        when(decoder.decode("FAIJWJSOOFAMAU", 5)).thenReturn(decodedMessage2);
        when(decoder.decode("STHSTSTVSASOS", 7)).thenReturn(decodedMessage3);
        when(decoder.decode("ABWSODVLKUPLE", 7)).thenReturn(decodedMessage4);
        when(decoder.decode("QLMPODTY", 6)).thenReturn(decodedMessage5);



        //we will consider Blaze as the new Kingdom and its emblem is Phoenix
        kingdomsAndEmblems.put("BLAZE", "Phoenix");

        RulerImpl ruler = new RulerImpl(kingdomsAndEmblems);

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