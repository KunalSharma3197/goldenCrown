package com.example.kunalsharma3197.goldencrown.decoder;

import java.util.LinkedList;
import java.util.List;

/**
 * MessageDecoder is a class whose object will be used to perform decoding of an encoded message.
 * decode method of the class contains the logic for decoding. The logic is based on caeser cipher.
 */

public class MessageDecoder {

    // char array to store english alphabets.
    private char[] alphabets;

    public MessageDecoder() {

        // storing english alphabets in the object. 
        alphabets = new char[] {'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y',
            'Z'
        };
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

        for (char ch : encodedMessage.toCharArray()) {    

            // if current character ch is not an alphabet or is lower case we ignore and move to 
            // next iteration because we are only concerned with decoding uppercase alphabet characters.
            if (!Character.isLetter(ch) || Character.isLowerCase(ch)){
                continue;
            }

            // position determines the index of current char in the alphabets object defined
            // as class attribute.
            int position = ch - 'A';

            // ch is decoded by determing the character present at index position - secretKey
            // however position - secretKey can be less than 0 hence we use (26 + position - secretKey) % 26
            decodedMessage.add(alphabets[(26 + position - secretKey) % 26]);
        }
        
        return decodedMessage;
    }
}