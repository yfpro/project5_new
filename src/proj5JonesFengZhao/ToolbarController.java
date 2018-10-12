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
    private CompileProcess compileProcess;
    private Thread runThread;
    private Process process;

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     *
     * @param curFile Reference to the currently selected file.
     */
    public void handleCompile(File curFile, IOConsole console, Button stopButton) throws  InterruptedException{
        stopButton.setDisable(false);
        compileProcess = new CompileProcess(curFile, console, stopButton, false);
        Thread compileThread = new Thread(compileProcess);
        compileThread.start();
//        this.process = compile.getProcess();
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     *
     * @param curFile Reference to the currently selected file.
     */
    public void handleCompileRun(File curFile, IOConsole console, Button stopButton) throws  InterruptedException{
        stopButton.setDisable(false);
        compileProcess = new CompileProcess(curFile, console, stopButton, true);
        Thread runThread = new Thread(compileProcess);
        runThread.start();
//        this.process = run.getProcess();
    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     */
    public void handleStop() {
        this.process = this.compileProcess.getProcess();
        System.out.println(this.process);
        if(this.process!=null) {
            try {
                process.getInputStream().close();
                process.getOutputStream().close();
                process.getErrorStream().close();
                this.process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Running code is stopping");
        }else{
            System.out.println("nothing to stop");
        }
    }
}
