package com.example.kunalsharma3197.goldencrown.pair;


/**
 * Container to ease passing around a tuple of two objects.
 */

public class Pair<F, S> {

    public final F first; // determines first value in pair.

    public final S second;// determines second value in pair.
    
    // constructor to set the class attributes first and second. 
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}