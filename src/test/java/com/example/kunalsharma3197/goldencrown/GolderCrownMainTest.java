package com.example.kunalsharma3197.goldencrown;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GolderCrownMainTest {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void testForAlliedKingdoms() throws IOException {

        String path1 = "src/test/resources/input1.txt";
        GoldenCrownMain.main(new String[]{path1});

        assertEquals("SPACE AIR LAND ICE ", outStream.toString());
    }
    @Test
    public void testForNoAlliedKingdoms() throws  IOException {
        String path2 = "src/test/resources/input2.txt";
        GoldenCrownMain.main(new String[]{path2});
        assertEquals("None ", outStream.toString());
    }
    @Test
    public void testForDuplicates() throws IOException {
        String path3 = "src/test/resources/input5.txt";
        GoldenCrownMain.main(new String[]{path3});
        assertEquals("None ", outStream.toString());
    }

    @Test
    public void testForFileNotFound() {
        String path4 = "src/test/resources/noSuchFile.txt";
        assertThrows(Exception.class,
                () -> GoldenCrownMain.main(new String[]{path4}));
    }
}
