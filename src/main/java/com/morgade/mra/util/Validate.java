package com.morgade.mra.util;

/**
 *
 * @author osboxes
 */
public class Validate {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object argument, String message) {
        isTrue( argument != null , message);
    }

    public static void notBlank(String argument, String message) {
        isTrue( argument != null && !argument.isEmpty(), message);
    }

    public static void inclusiveBetween(int min, int max, int value, String message) {
        isTrue( min <= value && max >= value, message);
    }
    
}
