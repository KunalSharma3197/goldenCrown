package com.example.kunalsharma3197.goldencrown.decoder;

import java.util.LinkedList;
import java.util.List;

/**
 * MessageDecoderImpl will be used to perform decoding of an encoded message.
 * decode method of the class contains the logic for decoding. The logic is based on caeser cipher.
 * MessageDecoder is designed for decoding of uppercase alphabets.
 * Numbers, lowercase alphabets and special characters wont be included in decoded message.
 * MessageDecoder can be extended for decoding of characters which are not included as of now.
 */

public class MessageDecoderImpl implements MessageDecoder {

    final int numberOfAlphabets = 26; // number of english alphabets.

    final char firstUpperCaseAlphabetCharacter = 'A'; // first alphabet character among all capital alphabets.

    public MessageDecoderImpl() {

    }

    /**
     * decode methods performs the task of decoding the message.
     * @param encodedMessage
     * @param secretKey
     * @return decodeMessage
     * For a given encodedMessage and secretKey the decode method determines the decoded message
     * and return it as a List<Character>
     */
    @Override
    public List<Character> decode(String encodedMessage, int secretKey) {

        List<Character> decodedMessage = new LinkedList<>();// list to store characters of decodedMessage.

        for (char character : encodedMessage.toCharArray()) {    
            
            // decodedCharacter will hold the decoded character.
            char decodedCharacter;
            
            // if character is not a letter or lowercase move to next iteration.
            if (!Character.isLetter(character) || Character.isLowerCase(character)) {
                continue;
            }

            // decodedCharacterPosition contains the position of character after decoding in alphabets.
            int decodedCharacterPosition = getDecodedCharacterPosition(character, secretKey);

            // determining the decoded character using decodedCharacterPosition and ASCII code of 'A'
            decodedCharacter = (char) (firstUpperCaseAlphabetCharacter + decodedCharacterPosition);
            decodedMessage.add(decodedCharacter);//adding the decoded character to the decodedMessage.
        }
        
        return decodedMessage; // return the decodedMessage
    }


    /**
     * @param character 
     * @param secretKey
     * @return position of character after shifting it by a value equal to secret key.
     *  getNewPosition gives us the new position of a character in the alphabets after it
     *  is being shifted by a value equal to secretKey.
     */
    public int getDecodedCharacterPosition(char character, int secretKey) {

        // originalCharacterPosition determines the character position in the alphabet.
        // for an uppercase charcter determine originalCharacterPosition using firstUpperCaseAlphabetCharacter.
        int originalCharacterPosition = character - firstUpperCaseAlphabetCharacter;

        //newAlphabetPosition determines the character's new positon in the alphabet.
        // using modulo we strictly remain in the alphabet range.
        int newAlphabetPosition = (
            numberOfAlphabets + originalCharacterPosition - secretKey) % numberOfAlphabets;

        return newAlphabetPosition; // return the new position
    }


}