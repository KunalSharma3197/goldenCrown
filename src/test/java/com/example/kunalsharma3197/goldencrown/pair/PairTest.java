package com.example.kunalsharma3197.goldencrown.pair;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Pair is a container to ease passing around a tuple of two objects.
 * This file tests the working of that containter.
 */
public class PairTest {

    /**
     * This test check if the correct values are stored in first and second fields of Pair
     */
    @Test
    public void checkPair() {
        Pair<String, String> pair = new Pair<>("Golden", "Crown");
        assertEquals("Golden", pair.first);
        assertEquals("Crown", pair.second);
    }

}