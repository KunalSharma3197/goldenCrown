package com.example.kunalsharma3197.goldencrown.decoder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MessageDecoderTest {
    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     */
    @Test
    public void decodeMessageTest() {

        String encodedMessage = "GOLDEN";
        MessageDecoder decoder = new MessageDecoder();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 5);
        List<Character> expected = Arrays.asList('B', 'J', 'G', 'Y', 'Z', 'I');
        assertEquals(expected, decodedMessage);
    }

    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of numbers.
     */

    @Test
    public void encodedMessageHasNumbersTest() {
        String encodedMessage = "1234";
        MessageDecoder decoder = new MessageDecoder();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 2);
        assertEquals(0, decodedMessage.size());
    }

    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of lowercase alphabets. 
     */
    @Test
    public void encodedMessageHasLowerCaseAlphabetsTest() {
        String encodedMessage = "abcd";
        MessageDecoder decoder = new MessageDecoder();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 6);
        assertEquals(0, decodedMessage.size());
    }

    /**
     * this test is used for testing the functionality of decode method of MessageDecoder
     * when encoded message consist of any possible character. 
     */
    @Test
    public void encodedMessageHasAnyPossibleCharacter() {
        String encodedMessage = "a1@ % BC";
        MessageDecoder decoder = new MessageDecoder();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 2);
        List<Character> expected = Arrays.asList('Z', 'A');
        assertEquals(2, decodedMessage.size());
        assertEquals(expected, decodedMessage);
    }

    /**
     * this test the functionality of decode method of MessageDecoder when encoded message consists
     * of all empty characters
     */
    @Test
    public void encodedMessageIsEmpty() {
        String encodedMessage = "        ";
        MessageDecoder decoder = new MessageDecoder();
        List<Character> decodedMessage = decoder.decode(encodedMessage, 10);
        assertEquals(0, decodedMessage.size());
    }

}