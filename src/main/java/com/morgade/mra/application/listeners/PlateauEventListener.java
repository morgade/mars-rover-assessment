package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventListener;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.Plateau;
import static java.lang.String.format;
import org.apache.commons.lang3.Validate;

/**
 * Defines a processor for the "Plateau" command
 *
 * @author Marcelo Burgos Morgade Cortizo
 */
public class PlateauEventListener implements MissionEventListener {
    public static final String EXPECTED_HEADER = "Plateau";
    private static final int EXPECTED_ARGUMENT_COUNT = 2;
    private static final int ARGUMENT_INDEX_WIDTH = 0;
    private static final int ARGUMENT_INDEX_HEIGHT = 1;

    @Override
    public void handle(MissionEvent event, MissionControl missionControl) {
        // Checks expected header
        if (!event.getHeader().equals(EXPECTED_HEADER)) {
            return;
        }
        
        // Checks argument count
        String[] arguments = event.getArgumentAsArray();
        Validate.isTrue(EXPECTED_ARGUMENT_COUNT == arguments.length, 
            format("Plateau event should contain %d arguments", EXPECTED_ARGUMENT_COUNT)
        );
        
        Plateau plateau = new Plateau(
                event.getArgumentAsInt(ARGUMENT_INDEX_WIDTH),
                event.getArgumentAsInt(ARGUMENT_INDEX_HEIGHT)
        );
        
        missionControl.resetMission(plateau);
    }

}
