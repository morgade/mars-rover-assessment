package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.Plateau;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for class PlateauEventListener
 * @author Marcelo Burgos Morgade Cortizo
 */
public class PlateauEventListenerTest {
    private PlateauEventListener listener;
    private MissionControl missionControl;
    
    @Before
    public void before() {
        listener = new PlateauEventListener();
        missionControl = mock(MissionControl.class);
    }
    
    @Test
    public void testHandle() {
        MissionEvent event = new MissionEvent("Plateau", "10 20");
        listener.handle(event, missionControl);
        
        ArgumentCaptor<Plateau> plateauCaptor = ArgumentCaptor.forClass(Plateau.class);
        verify(missionControl).resetMission(plateauCaptor.capture());
        assertEquals(10, plateauCaptor.getValue().getMaxX());
        assertEquals(20, plateauCaptor.getValue().getMaxY());
    }
    
    @Test
    public void testIgnoreNonPlateauEvent() {
        MissionEvent event = new MissionEvent("Rover1 Landing", "5 5 N");
        listener.handle(event, missionControl);
        
        verify(missionControl, times(0)).resetMission(any());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenArgumentCountIsInvalid() {
        MissionEvent event = new MissionEvent("Plateau", "10");
        listener.handle(event, missionControl);
    }
}
