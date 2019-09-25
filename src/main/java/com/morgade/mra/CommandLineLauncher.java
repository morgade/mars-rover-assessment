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
 * Application command line launcher
 * 
 * @author Marcelo Burgos Morgade Cortizo
 */
public class CommandLineLauncher {
    /**
     * JVM entry point
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        CommandLineLauncher commandLineLauncher = new CommandLineLauncher();
        try {
            commandLineLauncher.launch(args, System.out);
            System.exit(0);
        } catch (NavigationException | IllegalArgumentException | FileNotFoundException ex) {
            // Handle gracefully expected application exceptions
            System.err.println(ex.getMessage());
            System.exit(1);
        }
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
            System.err.println("Reading command from default input. Enter an empty line to end the program.");
            useEmpyLineMarker = true;
            input = System.in;
        } else if (args.length == 1) {
            input = new FileInputStream(args[0]);
        } else if (args.length > 1) {
            throw new IllegalArgumentException("This application doesn't handle more than one argument");
        }
        
        // Instantiate model and application components
        MissionControl missionControl = new MissionControl();
        MissionEventBus missionEventBus = MissionEventBusFactory.defaultMissionEventBus(missionControl);

        // Setup and run controller for text stream input interface
        TextLineStreamController controller = new TextLineStreamController(missionEventBus);
        controller.process(new Scanner(input), useEmpyLineMarker);

        // Setup and run a print stream output reporter
        MissionControlReporter reporter = new MissionControlReporter(missionControl);
        reporter.writeRoverReport(output);
    }
}
