package com.morgade.mra.model;

import com.morgade.mra.model.navigation.NavigationInstruction;
import com.morgade.mra.model.navigation.NavigationSystem2D;
import com.morgade.mra.model.navigation.PlateauNavigationSystem;
import com.morgade.mra.model.navigation.Position2D;
import com.morgade.mra.model.navigation.Direction2D;
import com.morgade.mra.model.navigation.NavigationException;
import com.morgade.mra.util.Validate;
import static java.lang.String.format;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
     * Defines behavior when handling NavigationExceptions (abort instruction sets
     * on NavigationException or simply skip moves that causes exceptions)
     */
    private boolean abortOnNavigationException;

    /**
     * Default constructor
     */
    public MissionControl() {
        this.rovers = new HashMap<>();
        this.abortOnNavigationException = false;
    }

    /**
     * Alternate Constructor
     * @param plateau initial plateau managed by the new mission control
     */
    public MissionControl(Plateau plateau) {
        this();
        Validate.notNull(this.plateau == null, "Plateau argument must be defined");
        this.plateau = plateau;
    }
       
    
    /**
     * Reset mission data, specifying a new managed plateau
     * @param plateau 
     */
    public void resetMission(Plateau plateau) {
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
        
        Arrays.stream(instructionSet).forEach(i -> processInstruction(navigationSystem, i));
    }
    
    /**
     * Delegates a navigation instruction to a navigation system
     * @param navigationSystem
     * @param navigationInstruction 
     */
    protected void processInstruction(NavigationSystem2D navigationSystem, NavigationInstruction navigationInstruction) {
        try {
            switch (navigationInstruction) {
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
        } catch (NavigationException ex) {
            if (this.abortOnNavigationException) {
                throw ex;
            } else {
                // TODO: Define a navigation exception logging system
            }
        }
    }

    /**
     * Setter for abortOnNavigationException
     * @param abortOnNavigationException 
     */
    public void setAbortOnNavigationException(boolean abortOnNavigationException) {
        this.abortOnNavigationException = abortOnNavigationException;
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
