package com.morgade.mra.model.navigation;

import static java.lang.String.format;
import java.util.Arrays;

/**
 * Models a simple 2D four directions enumeration in a clockwise order
 * @author Marcelo Burgos Morgade Cortizo
 */
public enum Direction2D {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");
    
    /**
     * Direction short name used in command lines
     */
    private final String shortName;

    /**
     * Enumeration constructor
     * @param shortName 
     */
    private Direction2D(String shortName) {
        this.shortName = shortName;
    }
    
    /**
     * Finds a direction by a short name
     * @param shortName
     * @return 
     */
    public static Direction2D findByShortName(String shortName) {
        return Arrays.stream(values())
                .filter( dir -> dir.shortName.equals(shortName) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(format("Invalid short name: %s", shortName)) );
    }
    
    /**
     * @return The new direction after a right turn
     */
    public Direction2D toRight() {
        // uses the clockwise enumeration order to find the new direction
        int rightIndex = (ordinal() + 1) % values().length;
        return values()[rightIndex];
    }
    
    /**
     * @return The new direction after a left turn
     */
    public Direction2D toLeft() {
        // uses the clockwise enumeration order to find the new direction
        int leftIndex = (values().length + ordinal() - 1) % values().length;
        return values()[leftIndex];
    }
}
