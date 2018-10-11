/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import java.io.File;
import java.sql.SQLOutput;


/**
 * This class is the controller for all of the toolbar functionality.
 * Specifically the Hello and Goodbye Buttons.
 *
 * @author Kevin Ahn
 * @author Jackie Hang
 * @author Matt Jones
 * @author Kevin Zhou
 * @version 1.0
 * @since 10-3-2018
 */
class ToolbarController {
    private Process process;

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     *
     * @param curFile Reference to the currently selected file.
     * @return compile successful or not
     */
    public void handleCompile(File curFile, IOConsole console) throws  InterruptedException{
        CompileProcess compile = new CompileProcess(curFile, console,false);
        Thread compileThread = new Thread(compile);
        compileThread.start();
        this.process = compile.getProcess();
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     *
     * @param curFile
     */
    public void handleCompileRun(File curFile, IOConsole console) throws  InterruptedException{
        CompileProcess run = new CompileProcess(curFile, console, true);
        Thread runThread = new Thread(run);
        runThread.start();
        this.process = run.getProcess();
    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     */
    public void handleStop() {
        if(this.process!=null) {
            this.process.destroy();
            System.out.println("Running code is stopping");
        }else{
            System.out.println("nothing to stop");
        }
    }

    /**
     * Helper function to build a process
     *
     * @param console
     * @param command
     * @return
     */
}
