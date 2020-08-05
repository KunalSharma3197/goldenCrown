package com.example.kunalsharma3197.goldencrown.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/* *
*  This file test the functionality of FileParser by using a sample txt file. 
* 
* e.g ------ >
*       input.txt -> AIR ROZO
                     GOLDEN CROWN
        String everything -> AIR ROZO
                             GOLDEN CROWN      
*/  
public class FileParserTest {

    /**
     * 
     * These test checks the working of FileParser for a file with contents.
     */
    private FileParser fileParser;

    @BeforeEach 
    public void init() {
        fileParser = new FileParser(); // initialising new instance of FileParser before every test.
    }
    @Test
    public void fileForParsingNotEmptyTest() throws IOException {

        // create a new File for the specified path
        File file = new File("src/test/resources/FileParserTestInput.txt"); 

        //parsing and storing contents of the file as string using absolute path for specified file.
        String contents = fileParser.getContentsOfFile(file.getAbsolutePath());


        //expected contains the correct String that will be generated after parsing the given file. 
        String expected = "AIR ROZO"
            + System.lineSeparator()
            + "LAND FAIJWJSOOFAMAU"
            + System.lineSeparator()
            + "ICE STHSTSTVSASOS"
            + System.lineSeparator();

        assertNotNull(contents);
        assertEquals(expected, contents);   
    }

    /**
     * 
     * This test checks that if the parsed data is empty or not in case passed file is empty.
     */
    @Test
    public void fileForParsingIsEmptyTest() throws IOException {

        // create a new File for the specified path
        File file = new File("src/test/resources/emptyFile.txt");

        //parsing and storing contents of the file as string using absolute path for specified file.
        String contents = fileParser.getContentsOfFile(file.getAbsolutePath());

        //expected contains the correct String that will be generated after parsing the given file
        // which is empty in this case.
        String expected = "";

        assertEquals(expected.length(), contents.length());

        assertEquals(expected, contents);
    }

    /**
     * This function checks how working of FileParser in case passed file doesn't exist. 
     */
    @Test
    public void fileForParsingDontExistTest() {

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            
            // create a new File for the specified path
            File file = new File("src/test/resources/noFile.txt");

            //parsing and storing contents of the file as string using absolute path for specified file.
            String contents = fileParser.getContentsOfFile(file.getAbsolutePath());
          });
        
    }
}