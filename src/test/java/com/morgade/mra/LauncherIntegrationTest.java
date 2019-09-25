package com.morgade.mra;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static java.lang.String.format;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Integration tests for the command line launcher
 * @see runTestCase for details on input/output data 
 * @author Marcelo
 */
public class LauncherIntegrationTest {
    
    @Test
    public void standard() {
        runTestCase("standard");
    }
    
    @Test
    public void threeRovers() {
        runTestCase("three-rovers");
    }
    
    @Test
    public void outBounds() {
        runTestCase("out-bounds");
    }
    
    @Test
    public void longRoute() {
        runTestCase("long-route");
    }
    
    /**
     * Utility to run an integration test using test resources in the format
     * /input-[id].txt  File with input commands
     * /output-[id].txt File with expected output
     * @param id 
     */
    public void runTestCase(String id)  {
        String inputFile = getResourceAbsolutePath(format("%s-input.txt", id));
        
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        try (PrintStream outputStream = new PrintStream(bufferStream)) {
            // Launch application
            CommandLineLauncher launcher = new CommandLineLauncher();
            launcher.launch(new String[]{inputFile}, outputStream);
            // Verify output
            Scanner expectedOutput = getResourceScanner(format("%s-output.txt", id));
            Scanner finalOutput = new Scanner(bufferStream.toString("utf8"));
            while (expectedOutput.hasNextLine() && finalOutput.hasNextLine()) {
                assertEquals(expectedOutput.nextLine(), finalOutput.nextLine());
            }
            
            assertTrue(!expectedOutput.hasNextLine());
            assertTrue(!finalOutput.hasNextLine());
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String getResourceAbsolutePath(String resource) {
        URL inputUrl = Thread.currentThread().getContextClassLoader().getResource(resource);
        try {
            return Paths.get(inputUrl.toURI()).toFile().getAbsolutePath();
        } catch (NullPointerException | URISyntaxException ex) {
            throw new RuntimeException("Resource path resolution failed", ex);
        }
    }
    
    private Scanner getResourceScanner(String resource) {
        return new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
    }
}
