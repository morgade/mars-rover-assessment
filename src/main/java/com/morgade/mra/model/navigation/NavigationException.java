package com.morgade.mra.model.navigation;

/**
 * Models a navigation exception
 * @author Marcelo Morgade
 */
public class NavigationException extends RuntimeException {
    public static final int CODE_UNDEFINED = 0;
    public static final int CODE_OUT_OF_BOUNDS = 1;

    /**
     * Navigation exception code
     */
    private final int code;
    
    /**
     * Main constructor
     * @param code
     * @param message 
     */
    public NavigationException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @return exception code
     */
    public int getCode() {
        return code;
    }

}
