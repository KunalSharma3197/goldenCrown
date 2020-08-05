package com.example.kunalsharma3197.goldencrown.decoder;

import java.util.LinkedList;
import java.util.List;

/**
 * MessageDecoder is a class whose object will be used to perform decoding of an encoded message.
 * decode method of the class contains the logic for decoding. The logic is based on caeser cipher.
 * MessageDecoder is capable of decoding both uppercase and lowercase alphabets.
 * Numbers and special characters wont be included in decoded message.
 */

public class MessageDecoder {

    static final int numberOfAlphabets = 26; // number of english alphabets.

    static final char firstUpperCaseAlphabetCharacter = 'A'; // first alphabet character among all capital alphabets.

    static final char firstLowerCaseAlphabetCharacter = 'a'; // first alphabet character among all lower case alphabets.

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

            // decodedChar contains the character after decoding. Intialised to an empty character.
            char decodedChar = ' ';

            // if  character is a UpperCase alphabet
            if (Character.isUpperCase(character)) {
   
                //decoding character using decodeUpperCaseAlphabet method.
                decodedChar = decodeUpperCaseAlphabet(character, secretKey);
            }
            //if character is a lowerCase alphabet
            else if (Character.isLowerCase(character)) {

                //decoding character using decodeLowerrCaseAlphabet method.
                decodedChar = decodeLowerCaseAlphabet(character, secretKey);
            }
            // if none of the above case satisfies move to next iteration
            else {
                continue;
            }

            decodedMessage.add(decodedChar);//adding the decoded character to the decodedMessage.
        }
        
        return decodedMessage;
    }

    /**
     * 
     * @param upperCaseCharacter
     * @param secretKey
     * @return decodedCharacter
     * decodeUpperrCaseAlphabet decodes the upper case character passed as argument using caeser cipher
     * The value by which the character will be shifter is determined by secret key.
     * e.g -->
     *     char K, secretKey = 2
     *     decodedChar = I 
     *  
     */

    private char decodeUpperCaseAlphabet(char upperCaseCharacter, int secretKey) {

        boolean isUpperCase = true; //flag to store case of character
        //using getNewPosition to get new alphabet position after shifting by value = secretKey
        int newAlphabetPosition = getNewPositon(upperCaseCharacter, secretKey, isUpperCase);

        //retrieve the new character by adding the new position to the ASCII code of letter A.
        char decodedCharacter = (char) (firstUpperCaseAlphabetCharacter + newAlphabetPosition);

        return decodedCharacter;//return the decoded character.
    }

    /**
     * 
     * @param lowerCaseCharacter
     * @param secretKey
     * @return decodedCharacter
     * decodeLowerCaseAlphabet decodes the lower case character passed as argument using caeser cipher
     * The value by which the character will be shifter is determined by secret key.
     * e.g -->
     *     char a, secretKey = 2
     *     decodedChar = y 
     *  
     */
    private char decodeLowerCaseAlphabet(char lowerCaseCharacter, int secretKey) {

        boolean isUpperCase = false; // flag to check case of character.
        //using getNewPosition to get new alphabet position after shifting by value = secretKey
        int newAlphabetPosition = getNewPositon(lowerCaseCharacter, secretKey, isUpperCase);
        
        //retrieve the new character by adding the new position to the ASCII code of letter A.
        char decodedCharacter = (char) (firstLowerCaseAlphabetCharacter + newAlphabetPosition);

        return decodedCharacter;//return the decoded character.
    }

    /**
     * getNewPosition gives us the new position of a character in the alphabets after it
     *  is being shifted by a value equal to secretKey.
     */
    private int getNewPositon(char character, int secretKey, boolean isUpperCase) {

         // originalCharacterPosition determines the character position in the alphabet.
        int originaCharacterPosition = 0;
        // if an uppercase charcter determine originalCharacterPosition using firstUpperCaseAlphabetCharacter.
        if (isUpperCase) {
            originaCharacterPosition = character - firstUpperCaseAlphabetCharacter;
        } else {
            // if a lowercase charcter determine originalCharacterPosition 
            // using firstLowererCaseAlphabetCharacter.
            originaCharacterPosition = character - firstLowerCaseAlphabetCharacter;
        }        
        //newAlphabetPosition determines the character's new positon in the alphabet.
        // using modulo we strictly remain in the alphabet range.
        int newAlphabetPosition = (
            numberOfAlphabets + originaCharacterPosition - secretKey) % numberOfAlphabets;

        return newAlphabetPosition; // return the new position
    }


}