package com.morgade.mra.model.navigation;

import com.morgade.mra.model.Plateau;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for class PlateauNavigationSystem
 * @author Marcelo
 */
public class PlateauNavigationSystemTest {
    private Plateau plateau;
    private Position2D startPosition;
    private Direction2D startDirection;
    private PlateauNavigationSystem plateauNavigationSystem;
    
    @Before
    public void before() {
        // Tests navigation start state
        this.plateau = new Plateau(10, 10);
        this.startPosition = new Position2D(5, 5);
        this.startDirection = Direction2D.EAST;
        
        this.plateauNavigationSystem = new PlateauNavigationSystem(plateau, startPosition, startDirection);
    }
    
    /**
     *  Tests valid moves
     */
    @Test
    public void testMove1() {
        this.plateauNavigationSystem.move();
        assertNavigationState(6, 5, Direction2D.EAST);
        
        this.plateauNavigationSystem.move();
        assertNavigationState(7, 5, Direction2D.EAST);
        
        this.plateauNavigationSystem.turnLeft();
        assertNavigationState(7, 5, Direction2D.NORTH);
        
        this.plateauNavigationSystem.move();
        assertNavigationState(7, 6, Direction2D.NORTH);
        
        this.plateauNavigationSystem.turnRight();
        assertNavigationState(7, 6, Direction2D.EAST);
        
        this.plateauNavigationSystem.turnRight();
        assertNavigationState(7, 6, Direction2D.SOUTH);
        
        this.plateauNavigationSystem.move();
        assertNavigationState(7, 5, Direction2D.SOUTH);
    }
    
    /**
     * Test moving out of bounds
     */
    @Test
    public void testMoveOutOfBounds() {
        this.plateauNavigationSystem.move();
        this.plateauNavigationSystem.move();
        assertNavigationState(7, 5, Direction2D.EAST);
        
        this.plateauNavigationSystem.move();
        this.plateauNavigationSystem.move();
        this.plateauNavigationSystem.move();
        assertNavigationState(10, 5, Direction2D.EAST);
        
        try {
            this.plateauNavigationSystem.move();
            fail("NavigationException was expected to be thrown");
        } catch (NavigationException navigationException) {
            assertEquals(navigationException.getCode(), NavigationException.CODE_OUT_OF_BOUNDS);
        }
    }
    
    private void assertNavigationState(int x, int y, Direction2D direction) {
        Position2D pos = this.plateauNavigationSystem.getCurrentPosition();
        assertEquals(pos.getX(), x);
        assertEquals(pos.getY(), y);
        assertEquals(this.plateauNavigationSystem.getCurrentDirection(), direction);
    }
}
