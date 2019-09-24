package com.morgade.mra.model.navigation;

import static java.lang.String.format;
import java.util.Arrays;

/**
 * Models the navigation 
 * @author Marcelo Burgos Morgade Cortizo
 */
public enum NavigationInstruction {
    MOVE("M"),
    TURN_LEFT("L"),
    TURN_RIGHT("R");
    
    private String id;

    private NavigationInstruction(String id) {
        this.id = id;
    }
    
    public static NavigationInstruction fromId(String id) {
        return Arrays.stream(values())
                .filter( n -> n.id.equals(id) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(format("Invalid instruction id: %s", id)));
    }
}
