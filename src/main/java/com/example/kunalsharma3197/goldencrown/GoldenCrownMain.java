package com.example.kunalsharma3197.goldencrown;

import com.example.kunalsharma3197.goldencrown.mapper.ObjectMapper;
import com.example.kunalsharma3197.goldencrown.mapper.StringMapper;
import com.example.kunalsharma3197.goldencrown.parser.FileParser;
import com.example.kunalsharma3197.goldencrown.solution.Ruler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * main class for running the program
 * path to input file is provided in args
 * @param String[] args
 */
public class GoldenCrownMain {
    
    public static void main(String[] args) throws IOException {

        FileParser fileParser = new FileParser(args[0]); // args[0] contains file path
        
        // Passing the path to file from args[0] to fileParse.parseInputFile
        // In order to get the data present in .txt file as String.
        String data = fileParser.parseInputFile();

        // objectMapper will be used to map the String data determined from the .txt
        // using FileParser to the required data structure
        ObjectMapper objectMapper = new StringMapper(data);

        // Creating new Instance of Ruler 
        Ruler ruler = new Ruler();

        // Determining the required ruler and list of allies using ruler.getRulerAndAllies()
        // List<Pair<String, String>> mapped using objectMapper is passed as argument
        Map<String,List<String>> rulerAndAllies = ruler.getRulerAndAllies(objectMapper
            .getKingdomsAndMessages());

        for (String king : rulerAndAllies.keySet()) {

            if (rulerAndAllies.get(king).get(0) != "None") {
                System.out.print(king + " "); // printing the ruling kingdom
            }            
            for (String ally : rulerAndAllies.get(king)) {
                System.out.print(ally + " "); // printing  allies of ruling kingdom
            }
        }
    }
}
