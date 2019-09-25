package com.morgade.mra.interfaces.stream;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventBus;
import static java.lang.String.format;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines a controller able to publish mission events read from a Scanner input
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class TextLineStreamController {
    /**
     * Command pattern
     */
    private static final Pattern REGEXP_COMMAND_LINE = Pattern.compile("(.+):(.+)");
    /**
     * Bus used to publish events
     */
    private final MissionEventBus missionEventBus;

    /**
     * Default controller
     * @param missionEventBus 
     */
    public TextLineStreamController(MissionEventBus missionEventBus) {
        this.missionEventBus = missionEventBus;
    }
    
    /**
     * Process all events available from a Scanner inpu
     * @param input Input scanner
     * @param useBlankLineMarker Option to stop reading scanner when empty line is detected
     */
    public void process(Scanner input, boolean useBlankLineMarker) {
        boolean endMarked = false;
        while (!endMarked && input.hasNextLine()) {
            String line = input.nextLine();
            
            // Check empty line to end processing on command line
            if (line.isEmpty() && useBlankLineMarker) {
                endMarked = true;
            } else {
                process(line);
            }
        }
    }
    
    /**
     * Process a single command line
     * @param line 
     */
    public void process(String line) {
        Matcher matcher = REGEXP_COMMAND_LINE.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(format("Invalid command line format: %s", line));
        }

        String headerSection = matcher.group(1);
        String argumentSection = matcher.group(2);
        // Build and publish a mission event
        MissionEvent event = new MissionEvent(headerSection, argumentSection);
        this.missionEventBus.publish(event);
    }
}
