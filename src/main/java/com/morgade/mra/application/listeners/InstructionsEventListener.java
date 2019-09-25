package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventListener;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.navigation.NavigationInstruction;
import com.morgade.mra.util.Validate;
import static java.lang.String.format;

/**
 * Defines a processor for the "Instructions" command
 * Header: "RoverId Instructions"
 * Arguments: "LMMMRMLMMR" (chain of navigation instructions)
 *
 * @author Marcelo Burgos Morgade Cortizo
 */
public class InstructionsEventListener implements MissionEventListener {
    public static final String EXPECTED_HEADER_VALUE = "Instructions";
    private static final int EXPECTED_HEADER_INDEX = 1;
    private static final int EXPECTED_HEADER_COUNT = 2;
    private static final int EXPECTED_ARGUMENT_COUNT = 1;
    private static final int HEADER_INDEX_ID = 0;
    private static final int ARGUMENT_INDEX_INSTRUCTIONS = 0;

    @Override
    public boolean handle(MissionEvent event, MissionControl missionControl) {
        // Check expected header
        String headers[] = event.getHeaderAsArray();
        if (EXPECTED_HEADER_COUNT != headers.length 
                || !headers[EXPECTED_HEADER_INDEX].equals(EXPECTED_HEADER_VALUE)) {
            return false;
        }
        
        // Check argument count
        String[] arguments = event.getArgumentAsArray();
        Validate.isTrue(EXPECTED_ARGUMENT_COUNT == arguments.length, 
            format("Instructions event should contain %d arguments", EXPECTED_ARGUMENT_COUNT)
        );
        
        String roverId = headers[HEADER_INDEX_ID];
        String instructions = arguments[ARGUMENT_INDEX_INSTRUCTIONS];
        
        missionControl.processInstructions(
                roverId,
                instructions.chars()
                    .mapToObj(ch -> String.valueOf((char) ch) )
                    .map( instruction -> NavigationInstruction.fromId(instruction) )
                    .toArray( l -> new NavigationInstruction[l] )
        );
        
        return true;
    }

}
