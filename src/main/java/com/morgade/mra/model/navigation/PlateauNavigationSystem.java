package com.morgade.mra.model.navigation;

import com.morgade.mra.model.Plateau;
import org.apache.commons.lang3.Validate;

/**
 * Implements a NavigationSystem2D that restrains its position to the boundaries
 * of a plateau
 * @author Marcelo
 */
public class PlateauNavigationSystem implements NavigationSystem2D {
    /**
     * Plateau being navigated
     */
    private final Plateau plateau;
    /**
     * Current position tracked by the navigation system
     */
    private Position2D currentPosition;
    /**
     * Current direction tracked by the navigation system
     */
    private Direction2D currentDirection;

    /**
     * Main constructor
     * @param plateau
     * @param startPosition
     * @param startDirection 
     */
    public PlateauNavigationSystem(Plateau plateau, Position2D startPosition, Direction2D startDirection) {
        Validate.notNull(plateau, "plateau parameter must be defined");
        Validate.notNull(startPosition, "startPosition parameter must be defined");
        Validate.notNull(startDirection, "startDirection parameter must be defined");
        Validate.isTrue( startPosition.isInsideBoundary(plateau.getWidth(), plateau.getHeight()) );
        
        this.plateau = plateau;
        this.currentPosition = startPosition;
        this.currentDirection = startDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2D getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction2D getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        Position2D newPosition = this.currentPosition.move(this.currentDirection);

        if (!newPosition.isInsideBoundary(this.plateau.getWidth(), this.plateau.getHeight())) {
            throw new NavigationException(NavigationException.CODE_OUT_OF_BOUNDS, "Out of the plateau bounds");
        }
        
        this.currentPosition = newPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnLeft() {
        this.currentDirection = this.currentDirection.toLeft();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnRight() {
        this.currentDirection = this.currentDirection.toRight();
    }
}
