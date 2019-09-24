package com.morgade.mra.application;

import com.morgade.mra.model.MissionControl;

/**
 * Defines a contract interface for mission event listeners
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public interface MissionEventListener {
    /**
     * This method is called for each mission event published by the application
     * 
     * @param event event to be handled
     * @param missionControl missionControl associated to the published event
     */
    void handle(MissionEvent event, MissionControl missionControl);
}
