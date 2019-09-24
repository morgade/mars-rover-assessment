package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.navigation.Direction2D;
import com.morgade.mra.model.navigation.Position2D;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for class LandingEventListener
 * @author Marcelo Burgos Morgade Cortizo
 */
public class LandingEventListenerTest {
    private LandingEventListener listener;
    private MissionControl missionControl;
    
    @Before
    public void before() {
        listener = new LandingEventListener();
        missionControl = mock(MissionControl.class);
    }
    
    @Test
    public void testHandle() {
        MissionEvent event = new MissionEvent("Curiosity Landing", "3 7 S");
        listener.handle(event, missionControl);
        
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Position2D> positionCaptor = ArgumentCaptor.forClass(Position2D.class);
        ArgumentCaptor<Direction2D> directionCaptor = ArgumentCaptor.forClass(Direction2D.class);
        verify(missionControl).registerLanding(idCaptor.capture(), positionCaptor.capture(), directionCaptor.capture());
        
        assertEquals("Curiosity", idCaptor.getValue());
        assertEquals(new Position2D(3, 7), positionCaptor.getValue());
        assertEquals(Direction2D.SOUTH, directionCaptor.getValue());
    }
    
    @Test
    public void testShouldIgnoreNonLandingEvents() {
        MissionEvent event = new MissionEvent("Plateau", "5 8");
        listener.handle(event, missionControl);
        
        event = new MissionEvent("Curiosity Instructions", "LMMLMRMM");
        listener.handle(event, missionControl);
        
        verify(missionControl, times(0)).registerLanding(anyString(), any(Position2D.class), any(Direction2D.class));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionOnInvalidArguments() {
        MissionEvent event = new MissionEvent("PathFinder Landing", "S 7 3");
        listener.handle(event, missionControl);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionOnInvalidArguments2() {
        MissionEvent event = new MissionEvent("PathFinder Landing", "4 6");
        listener.handle(event, missionControl);
    }
}
