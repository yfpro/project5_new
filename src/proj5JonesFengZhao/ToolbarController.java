/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import java.io.File;


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
    private Thread runThread;

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     *
     * @param curFile Reference to the currently selected file.
     * @return compile successful or not
     */
    void handleCompile(File curFile, IOConsole console) {
        Thread compileThread = new Thread(new CompileProcess(curFile, console));
        compileThread.start();
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     *
     * @param curFile
     */
    void handleCompileRun(File curFile, IOConsole console) {
        runThread = new Thread(new CompileRunProcess(curFile, console));
        runThread.start();
    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     * @param stopButton Reference to the Stop Button initialized in Main.fxml
     */
    void handleStop(Button stopButton) {
        runThread.interrupt();
        System.out.println("Running code is stopping");
    }

    /**
     * Helper function to build a process
     *
     * @param console
     * @param command
     * @return
     */
}
