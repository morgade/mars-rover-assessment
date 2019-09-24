package com.morgade.mra.application;

import java.io.Serializable;
import static java.lang.String.format;
import org.apache.commons.lang3.Validate;

/**
 * Model a simple event object that can be published by the application
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionEvent implements Serializable {
    private final String header;
    private final String argument;

    public MissionEvent(String header, String argument) {
        Validate.notBlank(header);
        Validate.notNull(argument);
        this.header = header;
        this.argument = argument;
    }

    public String getHeader() {
        return header;
    }

    public String getArgument() {
        return argument;
    }
    
    public String[] getHeaderAsArray() {
        return header.split("\\s+");
    }
    
    public String[] getArgumentAsArray() {
        return argument.split("\\s+");
    }

    public int getArgumentAsInt(int index) {
        String[] args = getArgumentAsArray();
        Validate.inclusiveBetween(0, args.length-1, index, format("Invalid argument index: %d", index));
        
        try {
            return Integer.parseInt(args[index]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(format("Argument '%s' is not a valid integer", args[index]));
        }
    }
}
