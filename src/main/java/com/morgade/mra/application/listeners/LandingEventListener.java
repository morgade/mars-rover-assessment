package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventListener;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.navigation.Direction2D;
import com.morgade.mra.model.navigation.Position2D;
import static java.lang.String.format;
import org.apache.commons.lang3.Validate;

/**
 * Defines a processor for the "Landing" command
 * Header: "RoverId Landing"
 * Arguments: "1 2 N" (x y Direction)
 *
 * @author Marcelo Burgos Morgade Cortizo
 */
public class LandingEventListener implements MissionEventListener {
    public static final String EXPECTED_HEADER_VALUE = "Landing";
    private static final int EXPECTED_HEADER_INDEX = 1;
    private static final int EXPECTED_HEADER_COUNT = 2;
    private static final int EXPECTED_ARGUMENT_COUNT = 3;
    private static final int HEADER_INDEX_ID = 0;
    private static final int ARGUMENT_INDEX_X = 0;
    private static final int ARGUMENT_INDEX_Y = 1;
    private static final int ARGUMENT_INDEX_DIRECTION = 2;

    @Override
    public void handle(MissionEvent event, MissionControl missionControl) {
        // Check expected header
        String headers[] = event.getHeaderAsArray();
        if (EXPECTED_HEADER_COUNT != headers.length 
                || !headers[EXPECTED_HEADER_INDEX].equals(EXPECTED_HEADER_VALUE)) {
            return;
        }
        
        // Check argument count
        String[] arguments = event.getArgumentAsArray();
        Validate.isTrue(EXPECTED_ARGUMENT_COUNT == arguments.length, 
            format("Landing event should contain %d arguments", EXPECTED_ARGUMENT_COUNT)
        );
        
        String roverId = headers[HEADER_INDEX_ID];
        Position2D position = new Position2D(
            event.getArgumentAsInt(ARGUMENT_INDEX_X),
            event.getArgumentAsInt(ARGUMENT_INDEX_Y)
        );
        Direction2D direction = Direction2D.findByShortName(arguments[ARGUMENT_INDEX_DIRECTION]);
        
        missionControl.registerLanding(roverId, position, direction);
    }

}
