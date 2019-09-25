package com.morgade.mra.application;

import com.morgade.mra.application.listeners.InstructionsEventListener;
import com.morgade.mra.application.listeners.LandingEventListener;
import com.morgade.mra.application.listeners.PlateauEventListener;
import com.morgade.mra.model.MissionControl;

/**
 * Simple event bus factory
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionEventBusFactory {

    public static void defaultMissionEventBus(MissionControl missionControl) {
        MissionEventBus missionEventBus = cleanMissionEventBus(missionControl);
        missionEventBus.registerListener(new PlateauEventListener());
        missionEventBus.registerListener(new LandingEventListener());
        missionEventBus.registerListener(new InstructionsEventListener());
    }
    
    public static MissionEventBus cleanMissionEventBus(MissionControl missionControl) {
        return new MissionEventBus(missionControl);
    }
}
