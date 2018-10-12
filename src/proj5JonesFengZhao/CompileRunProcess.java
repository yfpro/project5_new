/*
File: CompileRunProcess.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */


package proj5JonesFengZhao;

import javafx.scene.control.Button;

import java.io.File;

/**
 * This class extends the CompileProcess class which is a Runnable class and is used
 * to build a process that can be given to a Thread and run inside that Thread.
 * Specifically this class will override the run method to give it the ability to and
 * run a compile process followed by a run process contingent on the success of the
 * compile process.
 *
 * @author Matt Jones
 * @version 1.0
 * @since 10-10-2018
 */
public class CompileRunProcess extends CompileProcess {
    private File curFile;
    private IOConsole console;
    private Button stopButton;

    CompileRunProcess(File curFile, IOConsole console, Button stopButton) {
        super(curFile, console, stopButton);
        this.curFile = curFile;
        this.console = console;
        this.stopButton = stopButton;
    }

    /**
     * This will find the path of the selected file at the moment the compile stopButton
     * was pressed. The buildProcess function is then called to build a compile process
     * using the javac. If the compilation does not fail, second process will be
     * created that will run the compiled class file.
     */
    @Override
    public void run() {
        String path = curFile.getAbsolutePath();
        String[] compileCommand = {"javac", path};

        if (buildProcess(console, compileCommand)) {
            path = curFile.getAbsoluteFile().getParent();
            String fileName = curFile.getName();
            String[] runCommand = {"java", "-cp", path, fileName.substring(0,
                    fileName.length() - 5)};
            buildProcess(console, runCommand);
        }
        stopButton.setDisable(true);
    }
}
