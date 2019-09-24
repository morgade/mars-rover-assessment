package com.morgade.mra.model;

import com.morgade.mra.model.navigation.NavigationInstruction;
import com.morgade.mra.model.navigation.NavigationSystem2D;
import static java.lang.String.format;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/**
 * Models a mission control object that manages a set of rovers navigating a plateau
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionControl {
    private Plateau plateau;
    private final Map<String, Rover> rovers;

    public MissionControl() {
        rovers = new HashMap<>();
    }
    
    public void registerPlateau(Plateau plateau) {
        Validate.isTrue(this.plateau == null, "Mission control has already a registered plateau");
        this.plateau = plateau;
    }
    
    public void processInstructions(String roverId, NavigationInstruction ... instructionSet) {
        Rover rover = findRover(roverId);
        NavigationSystem2D navigationSystem = rover.getNavigationSystem();
        
        Arrays.stream(instructionSet).forEach( i -> {
            switch (i) {
                case MOVE:
                    navigationSystem.move();
                case TURN_LEFT:
                    navigationSystem.turnLeft();
                case TURN_RIGHT:
                    navigationSystem.turnRight();
            }
        });
        
        rover.getNavigationSystem().move();
    }
    
    protected Rover findRover(String roverId) {
        Validate.notNull(roverId, "Rover id not defined");
        Validate.isTrue(rovers.containsKey(roverId), format("Rover '%s' not registered in this mission control", roverId));
        return this.rovers.get(roverId);
    }
    
}
