/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;


/**
 * This class is the controller for all of the toolbar functionalities.
 * This class handles the actions of the Compile Button,
 * the Compile & Run Button, and the Stop Button.
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
    private CompileProcess compileProcess;
    private Process process;

    /**
     * compile the current file in a new thread
     * @param curFile the current file
     * @param console
     * @param stopButton
     * @throws InterruptedException
     */
    public void handleCompile(File curFile, IOConsole console, Button stopButton) {

        // enable the stop button
        stopButton.setDisable(false);

        // create a thread to compile the curFile
        compileProcess = new CompileProcess(curFile, console, stopButton, false);
        Thread compileThread = new Thread(compileProcess);
        compileThread.start();
    }

    /**
     * compile and run the curFile in a new thread
     * @param curFile
     * @param console
     * @param stopButton
     * @throws InterruptedException
     */
    public void handleCompileRun(File curFile, IOConsole console, Button stopButton) {

        // enable the stop button
        stopButton.setDisable(false);

        //create a thread to compile and run the current file
        compileProcess = new CompileProcess(curFile, console, stopButton, true);
        Thread runThread = new Thread(compileProcess);
        runThread.start();
    }

    /**
     * Will stop any compilation or running processes.
     *
     */
    public void handleStop() {
        if (this.compileProcess!=null) {
            this.process = this.compileProcess.getProcess();
            if (this.process != null) {
                try {
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    this.process.destroy();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
