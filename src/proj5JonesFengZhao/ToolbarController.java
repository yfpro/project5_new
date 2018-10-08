/*
File: ToolbarController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
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
     */
    void handleCompile(File curFile) throws IOException {
//        String path = curFile.getAbsoluteFile().getParent();
        String path = curFile.getAbsolutePath();
        System.out.println(path);

//        String command[] = {"javac ", path, "*.java"};
        ProcessBuilder pb = new ProcessBuilder("javac", path);

//        System.out.println(command);

        Process process = pb.start();

        // Check if any errors or compilation errors encounter then print on Console.
        if (process.getErrorStream().read() != -1) {
            System.out.println("Compilation Errors " + process.getErrorStream());
        }

        // Check if javac process execute successfully or Not
        //  0 - successful

        // I AM NOT SURE WHY BUT THIS BLOCH GIVES AN ERROR
//        if (process.exitValue() == 0) {
//            process = new ProcessBuilder(new String[]{"java", "-cp", "d:\\", "Test"}).start();
//            //Check if RuntimeException or Errors encounter during execution then print
//            // errors on console
//            // Otherwise print Output
//
//            if (process.getErrorStream().read() != -1) {
//                System.out.println("Errors " + process.getErrorStream());
//            } else {
//                System.out.println("Output " + process.getInputStream());
//            }
//        }
//        System.out.println("javac " + path + "*.java");
//        pb.command("javac ", path, "*.java");
    }

    /**
     * Will compile the code and print error codes in the terminal if necessary.
     * Otherwise, it will print compilation success.
     * If code compiles successfully, the code will be run.
     * @param compileRunButton Reference to the Compile and Run Button initialized in
     *                         Main.fxml
     */
    void handleCompileRun(Button compileRunButton) {
        System.out.println("Code is compiling and running");
    }

    /**
     * Will stop any code running through the Compile and Run button.
     *
     * @param stopButton Reference to the Stop Button initialized in Main.fxml
     */
    void handleStop(Button stopButton) {
        System.out.println("Running code is stopping");
    }
}
