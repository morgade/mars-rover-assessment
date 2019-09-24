package com.morgade.mra.model;

import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 * Unit tests for enum Direction2d
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Direction2DTest {
    @Test
    public void testFindByShortNameNorth() {
        Direction2D direction1 = Direction2D.findByShortName("N");
        assertSame(direction1, Direction2D.NORTH);
    }
    
    @Test
    public void testFindByShortNameEast() {
        Direction2D direction1 = Direction2D.findByShortName("E");
        assertSame(direction1, Direction2D.EAST);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindByShortNameInvalid() {
        Direction2D.findByShortName("X");
    }
    
    @Test
    public void testToRight() {
        assertSame(Direction2D.EAST, Direction2D.NORTH.toRight());
        assertSame(Direction2D.WEST, Direction2D.SOUTH.toRight());
        assertSame(Direction2D.NORTH, Direction2D.WEST.toRight());
        assertSame(Direction2D.SOUTH, Direction2D.EAST.toRight());
    }
    
    @Test
    public void testToLeft() {
        assertSame(Direction2D.WEST, Direction2D.NORTH.toLeft());
        assertSame(Direction2D.EAST, Direction2D.SOUTH.toLeft());
        assertSame(Direction2D.SOUTH, Direction2D.WEST.toLeft());
        assertSame(Direction2D.NORTH, Direction2D.EAST.toLeft());
    }
}
