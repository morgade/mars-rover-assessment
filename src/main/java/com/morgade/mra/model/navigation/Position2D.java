package com.morgade.mra.model.navigation;

import static java.lang.String.format;

/**
 * Models a immutable value object representing a position defined in a
 * boundless 2D space
 *
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Position2D {
    /**
     * x axis coordinate
     */
    private final int x;
    /**
     * y axis coordinate
     */
    private final int y;

    /**
     * Main constructor
     * @param x
     * @param y 
     */
    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Move method
     * @param direction Direction to move
     * @return A new position calculated after moving this position in a specified direction
     */
    public Position2D move(Direction2D direction) {
        int deltaX, deltaY = deltaX = 0;
        
        switch (direction) {
            case NORTH:
                deltaY++;
                break;
            case EAST:
                deltaX++;
                break;
            case SOUTH:
                deltaY--;
                break;
            case WEST:
                deltaX--;
                break;
        }
        
        return new Position2D(this.x + deltaX, this.y + deltaY);
    }
    
    /**
     * Calculates if this position is inside the specified boundary with an origin
     * at coordinate (0,0)
     * @param maxX maxX of the bounding box
     * @param maxY maxY of the bounding box
     * @return true if this position is inside the boundary
     */
    public boolean isInsideBoundary(int maxX, int maxY) {
        return this.isInsideBoundary(0, 0, maxX, maxY);
    }
    
    /**
     * Calculates if this position is inside the specified boundary
     * @param minX mixX of the bounding box
     * @param minY mixY of the bounding box
     * @param maxX maxX of the bounding box
     * @param maxY maxY of the bounding box
     * @return true if this position is inside the boundary
     */
    public boolean isInsideBoundary(int minX, int minY, int maxX, int maxY) {
        return this.x >= minX 
                && this.x <= maxX
                && this.y >= minY
                && this.y <= maxY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            final Position2D other = (Position2D) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return format("Position2D(%d,%d)", this.x, this.y);
    }
    
    
    
}
