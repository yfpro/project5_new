package proj5JonesFengZhao;

import javafx.application.Platform;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


public class CompileRunProcess implements Runnable {
    private File curFile;
    private IOConsole console;

    CompileRunProcess(File curFile, IOConsole console) {
        this.curFile = curFile;
        this.console = console;
    }

    public void run() {
        String path = curFile.getAbsolutePath();
        String[] compileCommand = {"javac", path};

        if (handleCompile(console, compileCommand)) {
            path = curFile.getAbsoluteFile().getParent();
            String fileName = curFile.getName();
            String[] runCommand = {"java", "-cp", path, fileName.substring(0,
                    fileName.length() - 5)};
            handleRun(console, runCommand);
        }
    }

    private Boolean handleCompile(IOConsole console, String[] command) {
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

    private void handleRun(IOConsole console, String[] command) {
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

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
