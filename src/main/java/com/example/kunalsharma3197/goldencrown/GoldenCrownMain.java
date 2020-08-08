package com.example.kunalsharma3197.goldencrown;

import com.example.kunalsharma3197.goldencrown.mapper.ObjectMapper;
import com.example.kunalsharma3197.goldencrown.mapper.StringMapper;
import com.example.kunalsharma3197.goldencrown.pair.Pair;
import com.example.kunalsharma3197.goldencrown.parser.FileParser;
import com.example.kunalsharma3197.goldencrown.solution.Ruler;
import com.example.kunalsharma3197.goldencrown.solution.RulerImpl;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * main class for running the program
 * path to input file is provided in args
 */
public class GoldenCrownMain {

    /**
     * 
     * @param path to file
     * @return contents of file as string
     * @throws IOException
     * this method takes path to a file as argument and return contents of the file at that path
     * as string.
     */
    static String parseFileAsString(String path) throws IOException {

        File file = new File(path); // create a new File for the specified path


        // Creating new instance of FileParser.
        FileParser fileParser = new FileParser();

        //parsing and returning contents of the file as string using absolute path for specified file.
        return fileParser.getContentsOfFile(file.getAbsolutePath());
    }

    /**
     *
     * @param resourceFileName
     * @return contentsOfResource
     * loads the contents of a specified resource file from a class path and return the contents of the resource as
     * String.
     */
     static String loadResourceFromClassPath(String resourceFileName) throws IOException {

         String contentsOfResource = new String();// this will contain contents of the requested resource.

         //GoldenCrownMain.class.getResourceAsStream() looks at the classpath for the given resource
         try (InputStream inputStream = GoldenCrownMain.class.getResourceAsStream(resourceFileName);

              //creating a BufferedReader to read text from inputStream.
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
              contentsOfResource = reader.lines()
                      .collect(Collectors.joining(System.lineSeparator()));

         }
         return contentsOfResource; // returning contents of the resource.
    }

    public static void main(String[] args) throws IOException {

        String pathToInputFile = args[0]; // args[0] contains input file path.

        // kingdomsAndMessagesData contains the content of input file as string
        String kingdomsAndMessagesData = parseFileAsString(pathToInputFile);


        final String resourceFileName = "/KingdomsAndEmblems.txt";// name of file to be loaded from resource.

        // kingdomsAndEmblemsData contains contents of KingdomsAndEmblems.txt as String
        String kingdomsAndEmblemsData = loadResourceFromClassPath(resourceFileName);

        // objectMapper will be used to map the String data determined from the .txt
        ObjectMapper objectMapper = new StringMapper();

        //we used objectMapper to map the kingdomsAndEmblemsData to kingdomsAndEmblems. 
        Map<String, String> kingdomsAndEmblems = objectMapper
            .getKingdomsAndEmblems(kingdomsAndEmblemsData);
        
        // Creating new Instance of Ruler using map of kingdoms and emblems
        Ruler ruler = new RulerImpl(kingdomsAndEmblems);

       // kingdomsAndMessages contains the content of kingdomsAndMessagesData mapped using objectMapper
        List<Pair<String, String>> kingdomsAndMessages = objectMapper
            .getKingdomsAndMessages(kingdomsAndMessagesData);
        
        // Determining the required ruler and list of allies using ruler.getRulerAndAllies()
        // List<Pair<String, String>> mapped using objectMapper is passed as argument
        Map<String,List<String>> rulerAndAllies = ruler.getRulerAndAllies(kingdomsAndMessages);

        for (String king : rulerAndAllies.keySet()) {

            if (!rulerAndAllies.get(king).get(0).equals("NONE")) {
                System.out.print(king + " "); // printing the ruling kingdom
            }            
            for (String ally : rulerAndAllies.get(king)) {
                System.out.print(ally + " "); // printing  allies of ruling kingdom
            }
        }
    }
}
