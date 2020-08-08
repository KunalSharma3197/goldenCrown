package com.example.kunalsharma3197.goldencrown.solution;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.List;
import java.util.Map;

/**
 * Ruler is an interface which contains signature of method getRulerAndAllies.
 * Any class implementing this interface will have to give its own implementation for the getRulerAndAllies method.
 * getRulerAndAllies method will contain the logic required to determine if a given ruler can form an alliance
 * with a given list of kingdoms or not.
 */
public interface Ruler {
    /**
     *
     * @param kingdomsAndMessages
     * @return rulerAndAllies
     */
     Map<String, List<String>> getRulerAndAllies(List<Pair<String, String>> kingdomsAndMessages);
}
