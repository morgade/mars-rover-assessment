package com.morgade.mra.model;

import com.morgade.mra.model.navigation.NavigationSystem2D;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for class Rover
 * @author Marcelo Burgos Morgade Cortizo
 */
public class RoverTest {
    
    @Test
    public void testConstructorValid() {
        NavigationSystem2D navigationSystem = mock(NavigationSystem2D.class);
        Rover r = new Rover("Rover1", navigationSystem);
        
        assertEquals(r.getId(), "Rover1");
        assertSame( r.getNavigationSystem(), navigationSystem);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid1() {
        NavigationSystem2D navigationSystem = mock(NavigationSystem2D.class);
        Rover r = new Rover(null, navigationSystem);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid2() {
        Rover r = new Rover("Rover1", null);
    }
    
}
