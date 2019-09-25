package com.morgade.mra.util;

/**
 * Utility validation class
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class Validate {
    /**
     * @param expression Expression to check
     * @param message Message to throw in the exception
     * @throws IllegalArgumentException if the expression is not true
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 
     * @param argument
     * @param message IllegalArgumentException if the argument is null
     */
    public static void notNull(Object argument, String message) {
        isTrue( argument != null , message);
    }

    /**
     * 
     * @param argument
     * @param message 
     * @throws IllegalArgumentException if the argument is blank or null
     */
    public static void notBlank(String argument, String message) {
        isTrue( argument != null && !argument.isEmpty(), message);
    }

    /**
     * 
     * @param min
     * @param max
     * @param value
     * @param message 
     * @throws IllegalArgumentException if value is not between(inclusive) min and max
     */
    public static void inclusiveBetween(int min, int max, int value, String message) {
        isTrue( min <= value && max >= value, message);
    }
    
}
