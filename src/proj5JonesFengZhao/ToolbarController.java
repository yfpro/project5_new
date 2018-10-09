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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
    boolean handleCompile(File curFile, StyleClassedTextArea console) throws IOException {
//        String path = curFile.getAbsoluteFile().getParent();
        String path = curFile.getAbsolutePath();
        System.out.println(path);

//        String command[] = {"javac ", path, "*.java"};


        try {
            ProcessBuilder pb = new ProcessBuilder("javac", path);
            pb.redirectErrorStream(true);

            File log = new File("./log.txt");
            pb.redirectOutput(log);

//        System.out.println(command);

            Process process = pb.start();
            console.appendText(getFileContent(log));


            BufferedReader br=new BufferedReader(
                    new InputStreamReader(
                            process.getInputStream()));
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        // Check if any errors or compilation errors encounter then print on Console.
//        if (process.getErrorStream().read() != -1) {
//            System.out.println("Compilation Errors " + process.getErrorStream());
//        }

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
     * @param curFile
     */
    void handleCompileRun(File curFile, StyleClassedTextArea console) {
        System.out.println("Code is compiling and running");
        String path = curFile.getAbsoluteFile().getParent();


        try{

            boolean compiled = handleCompile(curFile, console);
            if(compiled == true){
                try {

                    ProcessBuilder pb = new ProcessBuilder("java", path, "/Main.class");
                    pb.redirectErrorStream(true);

                    File log = new File("./log.txt");
                    pb.redirectOutput(log);


//        System.out.println(command);

                    Process process = pb.start();
                    console.appendText(getFileContent(log));


                    BufferedReader br=new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()));
                    String line;
                    while((line=br.readLine())!=null){
                        System.out.println(line);
                    }

                } catch (Exception ex) {
                    System.out.println(ex);

                }
            }
        }
        catch (IOException e){
            System.out.println(e);
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


    private String getFileContent(File file) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        } catch (IOException e) {
            UserErrorDialog userErrorDialog = new UserErrorDialog(
                    UserErrorDialog.ErrorType.READING_ERROR, file.getName());
            userErrorDialog.showAndWait();

        }
        return content;
    }
}
