package com.morgade.mra.application;

/**
 * Mission event bus contract interface
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public interface MissionEventBus {
    void publish(MissionEvent missionEvent);
}
