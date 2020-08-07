package com.example.kunalsharma3197.goldencrown.pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pair is a container to ease passing around a tuple of two objects.
 * This file tests the working of that containter.
 */
public class PairTest {

    /**
     * This test check if the correct values are stored in first and second fields of Pair
     */
    @Test
    public void testPair() {
        Pair<String, String> pair = new Pair<>("Golden", "Crown");
        assertEquals("Golden", pair.first);
        assertEquals("Crown", pair.second);
    }
    /**
     * following tests check the functionality of equals method of pair container
     */
    @Test
    public void testEqualPair() {
        Pair<String, String> pair1 = new Pair<>("First", "Pair");
        Pair<String, String> pair2 = new Pair<>("First", "Pair");
        assertTrue(pair1.equals(pair2));
    }
    @Test
    public void testUnEqualPair() {
        Pair<String, String> pair1 = new Pair<>("First", "Pair");
        Pair<String, String> pair2 = new Pair<>("Second", "Pair");
        assertFalse(pair1.equals(pair2));
    }

    @Test
    public void testEqualityForSamePair() {
        Pair<String, String> pair1 = new Pair<>("First", "Pair");
        assertTrue(pair1.equals(pair1));
    }
}