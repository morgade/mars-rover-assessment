package com.morgade.mra.model;

import com.morgade.mra.model.navigation.NavigationInstruction;
import com.morgade.mra.model.navigation.NavigationSystem2D;
import com.morgade.mra.model.navigation.PlateauNavigationSystem;
import com.morgade.mra.model.navigation.Position2D;
import com.morgade.mra.model.navigation.Direction2D;
import static java.lang.String.format;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/**
 * Models a mission control object that manages a set of rovers navigating on a plateau
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionControl {
    /**
     * plateau managed by this mission control
     */
    private Plateau plateau;
    /**
     * rovers navigating over the plateau
     */
    private final Map<String, Rover> rovers;

    /**
     * Default constructor
     */
    public MissionControl() {
        rovers = new HashMap<>();
    }

    /**
     * Alternate Constructor
     * @param plateau initial plateau managed by the new mission control
     */
    public MissionControl(Plateau plateau) {
        this();
        this.resetMission(plateau);
    }
       
    
    /**
     * Reset mission data, specifying a new managed plateau
     * @param plateau 
     */
    public final void resetMission(Plateau plateau) {
        Validate.notNull(this.plateau == null, "Plateau argument must be defined");
        this.plateau = plateau;
        this.rovers.clear();
    }
    
    /**
     * Register the landing of a rover on the plateau and start its navigation system
     * @param roverId
     * @param position
     * @param direction 
     * @return  An instance of the Rover class created to manage the landed rover
     */
    public Rover registerLanding(String roverId, Position2D position, Direction2D direction) {
        PlateauNavigationSystem navSystem = new PlateauNavigationSystem(plateau, position, direction);
        Rover rover = new Rover(roverId, navSystem);
        this.rovers.put(roverId, rover);
        
        return rover;
    }
    
    /**
     * Delegates a set of movement instructions to a rover
     * @param roverId
     * @param instructionSet 
     */
    public void processInstructions(String roverId, NavigationInstruction ... instructionSet) {
        Rover rover = findRover(roverId);
        NavigationSystem2D navigationSystem = rover.getNavigationSystem();
        
        Arrays.stream(instructionSet).forEach( i -> {
            switch (i) {
                case MOVE:
                    navigationSystem.move();
                    break;
                case TURN_LEFT:
                    navigationSystem.turnLeft();
                    break;
                case TURN_RIGHT:
                    navigationSystem.turnRight();
                    break;
            }
        });
        
    }

    /**
     * @return the registered plateau
     */
    public Plateau getPlateau() {
        return this.plateau;
    }
    
    /**
     * @return a set of registered rovers
     */
    public Collection<Rover> getRovers() {
        return this.rovers.values();
    }
    
    /**
     * Find a rover by id
     * @param roverId
     * @return rover of specified if
     * @throws IllegalArgumentException if provided roverId is not found
     */
    protected Rover findRover(String roverId) {
        Validate.notNull(roverId, "Rover id not defined");
        Validate.isTrue(rovers.containsKey(roverId), format("Rover '%s' not registered in this mission control", roverId));
        return this.rovers.get(roverId);
    }
    
}
