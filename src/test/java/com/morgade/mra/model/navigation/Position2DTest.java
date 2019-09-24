package com.morgade.mra.model.navigation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit tests for enum Direction2d
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Position2DTest {
    
    @Test
    public void testMoveNorth() {
        testMove(Direction2D.NORTH, 5, 5, 5, 6);
    }
    
    @Test
    public void testMoveWest() {
        testMove(Direction2D.WEST, 5, 5, 4, 5);
    }
    
    @Test
    public void testMoveSouth() {
        testMove(Direction2D.SOUTH, 5, 5, 5, 4);
    }
    
    @Test
    public void testMoveEast() {
        testMove(Direction2D.EAST, 5, 5, 6, 5);
    }
    
    protected void testMove(Direction2D d, int x1, int y1, int x2, int y2) {
        Position2D origin = new Position2D(x1, y1);
        Position2D destination = origin.move(d);
        assertEquals(destination.getX(), x2);
        assertEquals(destination.getY(), y2);
    }

    @Test
    public void testIsInsideBoundary() {
        Position2D origin = new Position2D(3, 5);
        assertTrue(origin.isInsideBoundary(10, 10));
        assertTrue(origin.isInsideBoundary(3, 5) );
        assertTrue(origin.isInsideBoundary(-5, -2, 3, 5) );
        assertTrue(!origin.isInsideBoundary(2, 10) );
        assertTrue(!origin.isInsideBoundary(4, 4, 10, 10) );
    }
    
    @Test
    public void testEquals() {
        Position2D p1 = new Position2D(2, 3);
        Position2D p2 = new Position2D(2, 3);
        Position2D p3 = new Position2D(3, 3);
        
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
        assertTrue(!p1.equals(p3));
        assertTrue(!p3.equals(p2));
    }
    
    
    @Test
    public void testHashCode() {
        Position2D p1 = new Position2D(2, 3);
        Position2D p2 = new Position2D(2, 3);
        Position2D p3 = new Position2D(3, 2);
        
        assertTrue(p1.hashCode() == p2.hashCode());
        assertTrue(p1.hashCode() != p3.hashCode());
    }
}
