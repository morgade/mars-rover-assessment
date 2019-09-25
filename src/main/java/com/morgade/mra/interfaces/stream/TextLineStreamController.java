package com.morgade.mra.interfaces.stream;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventBus;
import java.io.PrintStream;
import static java.lang.String.format;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marcelo
 */
public class TextLineStreamController {
    private static final Pattern REGEXP_COMMAND_LINE = Pattern.compile("(.+):(.+)");
    private final MissionEventBus missionEventBus;

    public TextLineStreamController(MissionEventBus missionEventBus) {
        this.missionEventBus = missionEventBus;
    }
    
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
    
    public void process(String line) {
        Matcher matcher = REGEXP_COMMAND_LINE.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(format("Invalid command line format: %s", line));
        }

        String headerSection = matcher.group(1);
        String argumentSection = matcher.group(2);
        MissionEvent event = new MissionEvent(headerSection, argumentSection);
        this.missionEventBus.publish(event);
    }
}
