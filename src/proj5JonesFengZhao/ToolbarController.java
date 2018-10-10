/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


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
    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     *
     * @param curFile Reference to the currently selected file.
     * @return compile successful or not
     */
    boolean handleCompile(File curFile, IOConsole console) {
        String path = curFile.getAbsolutePath();
        String[] command = {"javac", path};
        return (buildProcess(console, command));
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     *
     * @param curFile
     */
    void handleCompileRun(File curFile, IOConsole console) {
        String path = curFile.getAbsoluteFile().getParent();
        String fileName = curFile.getName();
        boolean compiled = handleCompile(curFile, console);

        if (compiled == true) {
            String[] command = {"java", "-cp", path, fileName.substring(0, fileName.length() - 5)};
            buildProcess(console, command);
        }
    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     * @param stopButton Reference to the Stop Button initialized in Main.fxml
     */
    void handleStop(Button stopButton) {
        System.out.println("Running code is stopping");
    }

    /**
     * Helper function to build a process
     *
     * @param console
     * @param command
     * @return
     */
    private boolean buildProcess(IOConsole console, String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            InputStream i = process.getErrorStream();
            console.readFrom(i);

            InputStream processOutput = process.getInputStream();
            console.readFrom(processOutput);

            OutputStream processInput = process.getOutputStream();
            console.setOutputStream(processInput);

            int errCode = process.waitFor();
            return errCode == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
