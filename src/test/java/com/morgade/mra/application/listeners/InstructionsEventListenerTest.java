package com.morgade.mra.application.listeners;

import com.morgade.mra.application.MissionEvent;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.navigation.Direction2D;
import com.morgade.mra.model.navigation.NavigationInstruction;
import static com.morgade.mra.model.navigation.NavigationInstruction.MOVE;
import static com.morgade.mra.model.navigation.NavigationInstruction.TURN_LEFT;
import static com.morgade.mra.model.navigation.NavigationInstruction.TURN_RIGHT;
import com.morgade.mra.model.navigation.Position2D;
import static org.junit.Assert.assertArrayEquals;
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
 * Unit tests for class InstructionsEventListener
 * @author Marcelo Burgos Morgade Cortizo
 */
public class InstructionsEventListenerTest {
    private InstructionsEventListener listener;
    private MissionControl missionControl;
    
    @Before
    public void before() {
        listener = new InstructionsEventListener();
        missionControl = mock(MissionControl.class);
    }
    
    @Test
    public void testHandle() {
        MissionEvent event = new MissionEvent("PathFinder Instructions", "MLMR");
        listener.handle(event, missionControl);
        
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<NavigationInstruction> instructionsCaptor = ArgumentCaptor.forClass(NavigationInstruction.class);
        verify(missionControl).processInstructions(idCaptor.capture(), instructionsCaptor.capture());
        
        assertEquals("PathFinder", idCaptor.getValue());
        assertArrayEquals(new NavigationInstruction[] {
                MOVE, TURN_LEFT, MOVE, TURN_RIGHT
            },
            instructionsCaptor.getAllValues().toArray()
        );
    }
    
    @Test
    public void testShouldIgnoreNonInstructionEvents() {
        MissionEvent event = new MissionEvent("Plateau", "5 8");
        listener.handle(event, missionControl);
        
        event = new MissionEvent("Curiosity Landing", "3 4 S");
        listener.handle(event, missionControl);
        
        verify(missionControl, times(0)).registerLanding(anyString(), any(Position2D.class), any(Direction2D.class));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionOnInvalidArguments() {
        MissionEvent event = new MissionEvent("PathFinder Instructions", "4 6");
        listener.handle(event, missionControl);
    }
}
