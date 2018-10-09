/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


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
//    private ProcessBuilder pb = new ProcessBuilder();

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * @param curFile Reference to the currently selected file.
     *
     * @return compile successful or not
     */
    boolean handleCompile(File curFile, IOConsole console)  {
        String path = curFile.getAbsolutePath();
        System.out.println(path);

        String[] command = {"javac",path};

        return(buildProcess(console, command));
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     * @param curFile
     */
    void handleCompileRun(File curFile, IOConsole console) {

        String path = curFile.getAbsoluteFile().getParent();
        String fileName = curFile.getName();

        boolean compiled = handleCompile(curFile, console);

        if(compiled == true){
            String[] command = {"java", "-cp",path,fileName.substring(0,fileName.length()-5)};
            buildProcess(console,command);

        }




    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     * @param stopButton Reference to the Stop Button initialized in Main.fxml
     */
    public void handleStop(Button stopButton) {
        System.out.println("Running code is stopping");
    }


    /**
     * Helper function to build a process
     * @param console
     * @param command
     * @return
     */
    public boolean buildProcess(IOConsole console, String[] command){
        try{
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            InputStream i = process.getErrorStream();
            console.readFrom(i);

            InputStream processOutput = process.getInputStream();
            console.readFrom(processOutput);

//            OutputStream processInput = process.getOutputStream();
//            console.writeTo(processInput);

            int errCode = process.waitFor();
            if (errCode == 0)return true;
            else return false;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;

        }
    }
}
