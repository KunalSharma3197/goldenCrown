package com.example.kunalsharma3197.goldencrown.mapper;

import com.example.kunalsharma3197.goldencrown.pair.Pair;

import java.util.List;
import java.util.Map;

/**
 * Object Mapper is an interface that contains method definition for mapping the
 * input to the desired data structure.
 */

public interface ObjectMapper {

    List<Pair<String, String>> getKingdomsAndMessages(String data);
    Map<String, String> getKingdomsAndEmblems(String data);

}