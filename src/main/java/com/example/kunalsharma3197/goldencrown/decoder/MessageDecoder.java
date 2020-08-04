package com.example.kunalsharma3197.goldencrown.decoder;

import java.util.LinkedList;
import java.util.List;

/**
 * MessageDecoder is a class whose object will be used to perform decoding of an encoded message.
 * decode method of the class contains the logic for decoding. The logic is based on caeser cipher.
 */

public class MessageDecoder {

    static final int numberOfAlphabets = 26; // number of english alphabets.

    static final char firstAlphabetCharacter = 'A'; // first alphabet character considering all capital alphabets

    public MessageDecoder() {

    }

    /**
     * decode methods performs the task of decoding the message.
     * @param encodedMessage
     * @param secretKey
     * @return decodeMessage
     * 
     */
    public List<Character> decode(String encodedMessage, int secretKey) {

        List<Character> decodedMessage = new LinkedList<>();

        for (char character : encodedMessage.toCharArray()) {    

            // if  character is not a alphabet or is lower case we move to next iteration
            if (Character.isLetter(character) == false || Character.isLowerCase(character)) {
                continue;
            }

            // originalCharacterPosition determines the character position in the alphabet.
            int originaCharacterPosition = character - firstAlphabetCharacter;

            //newAlphabetPosition determines the character's new positon in the alphabet.
            // using modulo we strictly remain in the alphabet range.
            int newAlphabetPosition = (
                numberOfAlphabets + originaCharacterPosition - secretKey) % numberOfAlphabets;
            
            //retrieve the new character by adding the new position to the ASCII code of letter A.
	        char decodedCharacter = (char) (firstAlphabetCharacter + newAlphabetPosition);

            decodedMessage.add(decodedCharacter);//add the decodedCharacter into decodedMessage.
        }
        
        return decodedMessage;
    }
}