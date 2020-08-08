package com.example.kunalsharma3197.goldencrown.decoder;

import java.util.List;
/**
 * MessageDecoder is an interface for decoding a message. Any class implementing MessageDecoder
 * must override the decode method and provide its own implementation for the same.
 */
public interface MessageDecoder {
    /**
     * 
     * @param encodedMessage
     * @param secretKey
     * @return decodedMessage
     */
     List<Character> decode(String encodedMessage, int secretKey);
}