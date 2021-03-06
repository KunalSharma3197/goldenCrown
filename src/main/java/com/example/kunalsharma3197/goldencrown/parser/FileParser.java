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

    public FileParser() {
        // no args constructor for FileParser
    }

    /**
     * 
     * @param pathToFile
     * @return String
     * @throws IOException
     * 
     */

    public  String getContentsOfFile(String pathToFile) throws IOException { 

        // Creating BufferedReader using FileReader and pathToFile
        // pathToFile is provided as param. pathToFile store's path of input file
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
        } finally {
            br.close();//closing the buffered reader
        }
        return everything; //returning the string with contents of the file
    }
}