package com.example.kunalsharma3197.goldencrown.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
     * This test checks the working of FileParser for a file with contents.
     */
    @Test
    public void parseInputFileTest() throws IOException {

        File file = new File("src/test/resources/FileParserTestInput.txt"); 
        FileParser fileParser = new FileParser(file.getAbsolutePath());
        String contents = fileParser.parseInputFile();
        String[] lines = contents.split(System.getProperty("line.separator"));

        assertNotNull(contents);
        assertEquals(3, lines.length);
        assertEquals("AIR ROZO", lines[0]);
        
    }

    /**
     * 
     * This test checks that if the parsed data is empty or not in case passed file is empty.
     */
    @Test
    public void emptyFileTest() throws IOException {
        File file = new File("src/test/resources/emptyFile.txt");
        FileParser fileParser = new FileParser(file.getAbsolutePath());
        String contents = fileParser.parseInputFile();
        String[] lines = contents.split(System.getProperty("line.separator"));
        String[] expected = {""};

        assertEquals(expected.length, lines.length);

        assertEquals(expected[0], lines[0]);
    }

    /**
     * This function checks how working of FileParser in case passed file doesn't exist. 
     */
    @Test
    public void noSuchFileTest() {

        File file = new File("src/test/resources/noFile.txt");
        Exception expected = new FileNotFoundException();
        Exception actual = null;
        try {
            FileParser fileParser = new FileParser(file.getAbsolutePath());
            String contents = fileParser.parseInputFile();
        } catch (Exception e) {
            actual = e;
        }
        assertEquals(expected.getCause(), actual.getCause());
    }
}