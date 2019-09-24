package com.morgade.mra.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;
import com.morgade.mra.model.navigation.Position2D;
import com.morgade.mra.model.navigation.Direction2D;
import static com.morgade.mra.model.navigation.NavigationInstruction.MOVE;
import static com.morgade.mra.model.navigation.NavigationInstruction.TURN_LEFT;
import static com.morgade.mra.model.navigation.NavigationInstruction.TURN_RIGHT;
import com.morgade.mra.model.navigation.NavigationSystem2D;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for class MissionControl
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionControlTest {
    
    @Test
    public void testConstructorValid() {
        Plateau plateau = new Plateau(10, 10);
        MissionControl missionControl = new MissionControl(plateau);
        
        assertSame(plateau, missionControl.getPlateau());
    }
    
    @Test
    public void testConstructorValid2() {
        MissionControl missionControl = new MissionControl();
        assertNull(missionControl.getPlateau());
    }
    
    @Test
    public void testLanding() {
        Plateau plateau = new Plateau(10, 10);
        MissionControl missionControl = new MissionControl(plateau);
        
        final String ROVER_ID = "Curiosity";
        Position2D position = new Position2D(5, 5);
        Rover rover = missionControl.registerLanding(ROVER_ID, position, Direction2D.NORTH);
        
        assertEquals(1, missionControl.getRovers().size());
        assertSame(rover, missionControl.getRovers().stream().findFirst().get());
        assertEquals(ROVER_ID, rover.getId());
        assertEquals(position, rover.getNavigationSystem().getCurrentPosition());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testLandingInvalid() {
        MissionControl missionControl = new MissionControl(new Plateau(10, 10));
        
        missionControl.registerLanding("Rover1", new Position2D(7, 15), Direction2D.EAST);
    }
    
    @Test
    public void testProcessInstructions() {
        MissionControl missionControl = new MissionControl(new Plateau(10, 10));
        
        final String ROVER_ID = "PathFinder";
        Rover rover = missionControl.registerLanding(ROVER_ID, new Position2D(5, 5), Direction2D.NORTH);
        NavigationSystem2D nav = rover.getNavigationSystem();
        
        missionControl.processInstructions(ROVER_ID, MOVE);
        assertEquals(new Position2D(5, 6), nav.getCurrentPosition());
        assertEquals(Direction2D.NORTH, nav.getCurrentDirection());
        
        missionControl.processInstructions(ROVER_ID, TURN_RIGHT, MOVE, MOVE, TURN_LEFT, TURN_LEFT, TURN_LEFT, MOVE);
        assertEquals(new Position2D(7, 5), nav.getCurrentPosition());
        assertEquals(Direction2D.SOUTH, nav.getCurrentDirection());
    }
    
}
