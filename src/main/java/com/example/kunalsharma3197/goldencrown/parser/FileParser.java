package com.example.kunalsharma3197.goldencrown.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* *
*   A class to parse the given text file and return it in a string format

* e.g ------ >
*       input.txt -> AIR ROZO
                     GOLDEN CROWN
        String    -> AIR ROZO
                     GOLDEN CROWN      
*/     
public class FileParser {

    /**
     * 
     * @param pathToFile
     * @return String
     * @throws IOException
     * 
     */
    private String pathToFile;// variable for storing path of input file
    
    public FileParser(String pathToFile) {
        this.pathToFile = pathToFile; // initialising pathToFile variable
    }

    public  String parseInputFile() throws IOException {

        // Creating BufferedReader using FileReader and pathToFile
        // pathToFile is provided as param.
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));

        String everything = new String();//this will contain the file contents.
        try {
            StringBuilder sb = new StringBuilder();// StringBuilder is used because String is immutable in java.
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();// converting StringBuilder object sb to String
        } catch (Exception e) {
            System.out.println(e);// printing exception if any.
        } finally {
            br.close();//closing the buffered reader
        }
        return everything; //returning the string with contents of the file
    }
}