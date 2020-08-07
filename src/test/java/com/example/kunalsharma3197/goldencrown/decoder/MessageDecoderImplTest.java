package com.example.kunalsharma3197.goldencrown.decoder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MessageDecoderImplTest {
    /**
     * these test are used for testing the functionality of decode method of MessageDecoder
     */
    MessageDecoderImpl decoder = new MessageDecoderImpl();// initialising new instance of MessageDecode before each test.
    @Test
    public void decodeMessageTest() {

        String encodedMessage = "GOLDEN"; //encoded message
        int secretkey = 5; // key used for decoding.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, secretkey);

        //expected contains the correct decoding of each character present in encodedMessage.
        List<Character> expected = Arrays.asList('B', 'J', 'G', 'Y', 'Z', 'I');
        assertEquals(expected, decodedMessage);
    }


 

    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of any possible character. 
     * As per our decoder definition it is designed to work for only uppercase alphabetical character.
     * So any number or special characters wont be included in the decoded message.
     */
    @Test
    public void testMessageWithSpecialCharacters() {
        String encodedMessage = "a1@ % BC";//encoded message
        int secretkey = 2; // key used for decoding.

        List<Character> decodedMessage = decoder.decode(encodedMessage, secretkey);
        //expected message contains these characters after decoding.
        // Numbers and special characters are not decoded hence are not included in decoded message.
        List<Character> expected = Arrays.asList('Z', 'A'); 
        assertEquals(expected, decodedMessage);
    }

    /**
     * this test the functionality of decode method of MessageDecoder when encoded message consists
     * of all empty characters
     */
    @Test
    public void testEncodedMessageIsEmpty() {
        String encodedMessage = "        "; //empty message
        int secretkey = 10; // key used for decoding.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, secretkey);
        assertEquals(0, decodedMessage.size());
    }
    /**
     * this test the functionality of decode method of MessageDecoder when encoded message consists
     * of all spaces.
     */
    @Test
    public void testMessageWithSpaces() {
        String encodedMessage = "FG H A P F"; // message with spaces
        int secretkey = 5; // key used for decoding.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, secretkey);

        //expected message contains these characters after decoding.
        List<Character> expected = Arrays.asList('A', 'B', 'C', 'V', 'K', 'A');

        assertEquals(expected, decodedMessage);
    }

}