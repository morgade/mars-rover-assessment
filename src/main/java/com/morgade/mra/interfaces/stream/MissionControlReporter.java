package com.morgade.mra.interfaces.stream;

import com.morgade.mra.model.MissionControl;
import java.io.PrintStream;

/**
 * A simple reporter class able to output MissionControl data to a PrintStream
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionControlReporter {
    private MissionControl missionControl;

    public MissionControlReporter(MissionControl missionControl) {
        this.missionControl = missionControl;
    }
    
    /**
     * Writes a rover report to a PrintStream
     * @param output 
     */
    public void writeRoverReport(PrintStream output) {
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
