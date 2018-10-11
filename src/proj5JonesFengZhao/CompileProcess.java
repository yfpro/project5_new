package proj5JonesFengZhao;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


public class CompileProcess implements Runnable {
    private File curFile;
    private IOConsole console;

    CompileProcess(File curFile, IOConsole console) {
        this.curFile = curFile;
        this.console = console;
    }

    public void run() {
        String path = curFile.getAbsolutePath();
        String[] command = {"javac", path};
        buildProcess(console, command);
    }

    private void buildProcess(IOConsole console, String[] command) {
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
