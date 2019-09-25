package com.morgade.mra.application.bus;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.application.MissionEventBus;
import com.morgade.mra.application.MissionEventListener;
import com.morgade.mra.model.MissionControl;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a simple mission control event bus
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class SimpleMissionEventBus implements MissionEventBus {
    private final List<MissionEventListener> listeners;
    private final MissionControl missionControl;
    
    protected SimpleMissionEventBus(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.listeners = new ArrayList<>();
    }
    
    public void registerListener(MissionEventListener listener) {
        this.listeners.add(listener);
    }
    
    @Override
    public void publish(MissionEvent missionEvent) {
        this.listeners.stream().forEach(
            l -> l.handle(missionEvent, this.missionControl)
        );
    }
}
