package com.morgade.mra.model.navigation;

/**
 * Define a contract interface for a simple 2D navigation system
 * 
 * @author Marcelo Morgade
 */
public interface NavigationSystem2D {
    /**
     * @return The current position tracked by the navigation system
     */
    Position2D getCurrentPosition();
    
    /**
     * @return The current direction tracked by the navigation system
     */
    Direction2D getCurrentDirection();
    
    /**
     * Request the navigation system to move based on the current direction
     * @throws NavigationException when the movement requested is not possible
     */
    void move();
    
    /**
     * Request the navigation system to turn left based on the current direction
     */
    void turnLeft();
    
    /**
     * Request the navigation system to turn right based on the current direction
     */
    void turnRight();
}
