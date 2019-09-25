package com.morgade.mra.model;

import com.morgade.mra.util.Validate;


/**
 * Models an immutable plateau value object
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Plateau {
    /**
     * width in 2D coordinate units
     */
    private final int maxX;
    /**
     * heigh in 2D coordinate units
     */
    private final int maxY;

    /**
     * Main constructor
     * @param width
     * @param height 
     */
    public Plateau(int width, int height) {
        Validate.isTrue(width > 0, "Plateau width must be greater than 0");
        Validate.isTrue(height > 0, "Plateau width must be greater than 0");
        this.maxX = width;
        this.maxY = height;
    }
    
    /**
     * @return width value
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @return height value
     */
    public int getMaxY() {
        return maxY;
    }
}
