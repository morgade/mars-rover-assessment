package com.morgade.mra.model;

import com.morgade.mra.model.navigation.NavigationSystem2D;
import org.apache.commons.lang3.Validate;

/**
 * Models a Rover object capable of navigating in a 2D coordinate space
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Rover {
    /**
     * Rover id
     */
    private final String id;
    /**
     * Rover navigation system
     */
    private final NavigationSystem2D navigationSystem;

    /**
     * Default constructor
     * @param id
     * @param navigationSystem 
     */
    public Rover(String id, NavigationSystem2D navigationSystem) {
        Validate.notBlank(id, "Rover id must be defined");
        Validate.notNull(navigationSystem, "Rover navigationSystem must be defined");
        this.id = id;
        this.navigationSystem = navigationSystem;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return navigationSystem
     */
    public NavigationSystem2D getNavigationSystem() {
        return navigationSystem;
    }
    
}
