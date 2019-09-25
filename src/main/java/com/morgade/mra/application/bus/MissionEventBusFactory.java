package com.morgade.mra.application.bus;

import com.morgade.mra.application.MissionEventBus;
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

    public static MissionEventBus defaultMissionEventBus(MissionControl missionControl) {
        SimpleMissionEventBus missionEventBus = new SimpleMissionEventBus(missionControl);
        missionEventBus.registerListener(new PlateauEventListener());
        missionEventBus.registerListener(new LandingEventListener());
        missionEventBus.registerListener(new InstructionsEventListener());
        return missionEventBus;
    }
    
    public static MissionEventBus cleanMissionEventBus(MissionControl missionControl) {
        return new SimpleMissionEventBus(missionControl);
    }
}
