package com.morgade.mra.model;

import com.morgade.mra.util.Validate;


/**
 * Models an immutable plateau value object
 * @author Marcelo Morgade
 */
public class Plateau {
    /**
     * width in 2D coordinate units
     */
    private final int width;
    /**
     * heigh in 2D coordinate units
     */
    private final int height;

    /**
     * Main constructor
     * @param width
     * @param height 
     */
    public Plateau(int width, int height) {
        Validate.isTrue(width > 0, "Plateau width must be greater than 0");
        Validate.isTrue(height > 0, "Plateau width must be greater than 0");
        this.width = width;
        this.height = height;
    }
    
    /**
     * @return width value
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height value
     */
    public int getHeight() {
        return height;
    }
}
