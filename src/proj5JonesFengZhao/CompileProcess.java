/*
File: CompileProcess.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */


package proj5JonesFengZhao;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class implements the Runnable class and is used to build a process that can be
 * given to a Thread and run inside that Thread.
 * Specifically this class will build and run a compile process.
 *
 * @author Matt Jones
 * @version 1.0
 * @since 10-10-2018
 */
public class CompileProcess implements Runnable {
    private File curFile;
    private IOConsole console;


    CompileProcess(File curFile, IOConsole console) {
        this.curFile = curFile;
        this.console = console;
    }

    /**
     * This will find the path of the selected file at the moment the compile button
     * was pressed. The buildProcess function is then called to build a compile process
     * using the javac.
     */
    public void run() {
        String path = curFile.getAbsolutePath();
        String[] command = {"javac", path};
        buildProcess(console, command);
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
    public boolean buildProcess(IOConsole console, String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            InputStream i = process.getErrorStream();
            console.readFrom(i);

            InputStream processOutput = process.getInputStream();
            console.readFrom(processOutput);

            OutputStream processInput = process.getOutputStream();
            console.writeTo(processInput);

            int errCode = process.waitFor();
            return errCode == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
