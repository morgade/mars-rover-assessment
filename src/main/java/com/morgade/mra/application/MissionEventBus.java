package com.morgade.mra.application;

import com.morgade.mra.application.listeners.InstructionsEventListener;
import com.morgade.mra.application.listeners.LandingEventListener;
import com.morgade.mra.application.listeners.PlateauEventListener;
import com.morgade.mra.model.MissionControl;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a simple mission control event bus
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class MissionEventBus {
    private final List<MissionEventListener> listeners;
    private final MissionControl missionControl;
    
    protected MissionEventBus(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.listeners = new ArrayList<>();
    }
    
    public final void registerListener(MissionEventListener listener) {
        this.listeners.add(listener);
    }
    
    public void publish(MissionEvent missionEvent) {
        this.listeners.stream().forEach(
            l -> l.handle(missionEvent, this.missionControl)
        );
    }
}
