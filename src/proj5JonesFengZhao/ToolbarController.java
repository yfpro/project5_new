/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;


/**
 * This class is the controller for all of the toolbar functionality.
 * Specifically the Hello and Goodbye Buttons.
 *
 * @author Kevin Ahn
 * @author Jackie Hang
 * @author Matt Jones
 * @author Kevin Zhou
 * @author Yi Feng
 * @author Danqing Zhao
 * @version 1.0
 * @since 10-3-2018
 */
public class ToolbarController {
    private Thread compileThread;
    private Thread runThread;

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     *
     * @param curFile Reference to the currently selected file.
     */
    public void handleCompile(File curFile, IOConsole console, Button stopButton) {
        stopButton.setDisable(false);
        compileThread = new Thread(new CompileProcess(curFile, console, stopButton));
        compileThread.start();
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     *
     * @param curFile Reference to the currently selected file.
     */
    public void handleCompileRun(File curFile, IOConsole console, Button stopButton) {
        stopButton.setDisable(false);
        runThread = new Thread(new CompileRunProcess(curFile, console, stopButton));
        runThread.start();
    }

    /**
     * Will stop any code running through the Compile and Run button.
     */
    public void handleStop() {
        compileThread.interrupt();
        runThread.interrupt();
    }
}
