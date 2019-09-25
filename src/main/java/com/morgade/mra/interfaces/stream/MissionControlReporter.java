package com.morgade.mra.interfaces.stream;

import com.morgade.mra.model.MissionControl;
import java.io.PrintStream;

/**
 *
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionControlReporter {
    private MissionControl missionControl;

    public MissionControlReporter(MissionControl missionControl) {
        this.missionControl = missionControl;
    }
    
    public void writeReport(PrintStream output) {
        this.missionControl.getRovers()
            .forEach( 
                rover -> output.printf("%s:%d %d %s\r\n", 
                            rover.getId(),
                            rover.getNavigationSystem().getCurrentPosition().getX(),
                            rover.getNavigationSystem().getCurrentPosition().getY(),
                            rover.getNavigationSystem().getCurrentDirection().getShortName()
                        )
            );
                
    }
}
