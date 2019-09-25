package com.morgade.mra.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit tests for class Plateau
 * @author Marcelo Morgade
 */
public class PlateauTest {
    @Test
    public void testConstructorValid() {
        Plateau plateau = new Plateau(20, 30);
        assertEquals(plateau.getMaxX(), 20);
        assertEquals(plateau.getMaxY(), 30);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroWidth() {
        Plateau plateau = new Plateau(0, 30);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHeight() {
        Plateau plateau = new Plateau(20, -5);
    }
}
