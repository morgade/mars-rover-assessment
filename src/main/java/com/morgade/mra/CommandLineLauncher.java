package com.morgade.mra;

import com.morgade.mra.application.MissionEventBus;
import com.morgade.mra.application.bus.MissionEventBusFactory;
import com.morgade.mra.interfaces.stream.MissionControlReporter;
import com.morgade.mra.interfaces.stream.TextLineStreamController;
import com.morgade.mra.model.MissionControl;
import com.morgade.mra.model.navigation.NavigationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Marcelo Cortizo
 */
public class CommandLineLauncher {
    /**
     * JVM entry point
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        CommandLineLauncher commandLineLauncher = new CommandLineLauncher();
        commandLineLauncher.launch(args, System.out);
    }
    
    /**
     * Launch the command line application interface
     * @param args
     * @param output
     * @throws FileNotFoundException 
     */
    public void launch(String[] args, PrintStream output) throws FileNotFoundException {
        InputStream input = null;
        boolean useEmpyLineMarker = false;
        
        if (args.length == 0) {
            System.err.println("Reading command from default input. Type an empty line to end the program.");
            useEmpyLineMarker = true;
            input = System.in;
        } else if (args.length == 1) {
            input = new FileInputStream(args[0]);
        } else if (args.length > 1) {
            System.err.println("Usage: java -jar mars-rover-asessment.jar [input-file]");
            return;
        }
        
        try {
            MissionControl missionControl = new MissionControl();
            MissionEventBus missionEventBus = MissionEventBusFactory.defaultMissionEventBus(missionControl);

            TextLineStreamController controller = new TextLineStreamController(missionEventBus);
            controller.process(new Scanner(input), useEmpyLineMarker);

            MissionControlReporter reporter = new MissionControlReporter(missionControl);
            reporter.writeReport(output);
        } catch (NavigationException | IllegalArgumentException ex) {
            output.println(ex.getMessage());
        }
    }
}
