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

    /**
     * overriding equals method to compare two pair objects.
     * @param obj
     * @return boolean
     * equals method is used to compare two pair objects.
     * it returns true if both pair under comparison are same or their contents are same.
     * return false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true; // return true if both pair objects are same. 
        }
        
        if (obj instanceof Pair) { // if two pair objects are not same we compare their contents.
            Pair<F,S> other = (Pair<F,S>) obj; 

            return (this.first.equals(other.first)) 
                && (this.second.equals(other.second)) ;// return true if both first and second values of both pairs are equal.
        }
        return false; // returning false because the contents of both pairs are not same.
    }
}