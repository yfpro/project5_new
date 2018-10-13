/*
File: CompileProcess.java
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
 * This class implements the Runnable class and is used to build a process that can be
 * given to a Thread and run inside that Thread.
 * Specifically this class will build and run a compile process.
 *
 * @author Matt Jones
 * @author Yi Feng
 * @author Danqing Zhao
 * @version 1.0
 * @since 10-10-2018
 */

public class CompileProcess implements Runnable {
    private File curFile;
    private IOConsole console;
    private Button stopButton;
    private Process process;
    private boolean ifRun;

    /**
     * Constructor
     * @param curFile the file to be compiled (and run)
     * @param console the console
     * @param stopButton the stopButton
     * @param ifRun if ifRun is false, it only compiles the file
     *              if ifRun is true, it both compiles and runs the file
     */
    CompileProcess(File curFile, IOConsole console, Button stopButton, boolean ifRun) {
        this.curFile = curFile;
        this.console = console;
        this.ifRun = ifRun;
        this.stopButton = stopButton;
    }

    /**
     * This will find the path of the selected file at the moment the compile button
     * was pressed. The buildProcess function is then called to build a compile process
     * using the javac.
     */
    public void run() {
        String path = curFile.getAbsolutePath();
        String[] command = {"javac", path};
        this.process = buildProcess(console, command);
        if(ifRun){
            if (this.process!=null) {
                path = curFile.getAbsoluteFile().getParent();
                String fileName = curFile.getName();
                String[] runCommand = {"java", "-cp", path, fileName.substring(0,
                        fileName.length() - 5)};
                this.process = buildProcess(console, runCommand);
            }
        }
        stopButton.setDisable(true);
    }

    /**
     * Getter of process
     * @return Process
     */
    public Process getProcess(){
        return(this.process);
    }

    /**
     * Builds a process using a ProcessBuilder. Starts the process and passes the
     * InputStream and OutputStream to an IOConsole given by the console parameter.
     *
     * @param console Reference to an IOConsole that will be used for user input to the
     *                process. All output from the process will also be directed to the
     *                console
     * @param command Reference to a command that will be passed to the ProcessBuilder
     * @return Will return a boolean contingent on the success of the process. For
     * example, if the compile process fails, will return false.
     */
    public Process buildProcess(IOConsole console, String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // let the console read from the process's error stream
            InputStream processErrorStream = process.getErrorStream();
            console.readFrom(processErrorStream);

            // let the console read from the process's output stream
            InputStream processOutput = process.getInputStream();
            console.readFrom(processOutput);

            // set the process's input stream to the console's output stream
            OutputStream processInput = process.getOutputStream();
            console.setOutputStream(processInput);
            this.process = process;

            //wait for the process to complete
            process.waitFor();

            return(process);
        } catch (Exception e) {
            e.printStackTrace();
            return(null);
        }
    }
}
