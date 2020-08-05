package com.example.kunalsharma3197.goldencrown.decoder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MessageDecoderTest {
    /**
     * these test are used for testing the functionality of decode method of MessageDecoder
     */
  
    @Test
    public void decodeMessageTest() {

        String encodedMessage = "GOLDEN"; //encoded message 

        MessageDecoder decoder = new MessageDecoder();// initialising new instance of MessageDecode before each test.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, 5);

        //expected contains the correct decoding of each character present in encodedMessage.
        List<Character> expected = Arrays.asList('B', 'J', 'G', 'Y', 'Z', 'I');
        assertEquals(expected, decodedMessage);
    }


    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of lowercase alphabets. 
     */
    @Test
    public void encodedMessageHasLowerCaseAlphabetsTest() {
        String encodedMessage = "abcd";

        MessageDecoder decoder = new MessageDecoder();// initialising new instance of MessageDecode before each test.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, 4);
        assertEquals(4, decodedMessage.size());
        List<Character> expected = Arrays.asList('w', 'x', 'y', 'z');
        assertEquals(expected, decodedMessage);
    }

    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of any possible character. 
     * As per our decoder definition it is designed to work for only alphabetical character.
     * So any number or special characters wont be included in the decoded message.
     */
    @Test
    public void encodedMessageHasAnyPossibleCharacter() {
        String encodedMessage = "a1@ % BC";//encoded message

        MessageDecoder decoder = new MessageDecoder();// initialising new instance of MessageDecode before each test.

        List<Character> decodedMessage = decoder.decode(encodedMessage, 2);
        //expected message contains these characters after decoding.
        // Numbers and special characters are not decoded hence are not included in decoded message.
        List<Character> expected = Arrays.asList('y', 'Z', 'A'); 
        assertEquals(3, decodedMessage.size());
        assertEquals(expected, decodedMessage);
    }

    /**
     * this test the functionality of decode method of MessageDecoder when encoded message consists
     * of all empty characters
     */
    @Test
    public void encodedMessageIsEmpty() {
        String encodedMessage = "        "; //empty message

        MessageDecoder decoder = new MessageDecoder();// initialising new instance of MessageDecode before each test.

        //decodedMessage contains each character from encodedMessage after decoding.
        List<Character> decodedMessage = decoder.decode(encodedMessage, 10);
        assertEquals(0, decodedMessage.size());
    }

}